package psp.a51.sec;

import psp.a51.model.Roles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * Gestor de usuarios y autenticación.
 * Esta clase maneja el registro, búsqueda y autenticación de usuarios
 * utilizando almacenamiento seguro con hash de contraseñas (PBKDF2) y salt.
 * Los datos se persisten en el archivo /data/myShadow.txt con formato:
 * {@code username:rol:salt:hash}
 *
 * @author PSP - DAM2
 * @version 1.0
 * @see psp.a51.sec.PasswordHasher
 * @see psp.a51.sec.FilesManager
 * @see psp.a51.model.Roles
 */
public class UsersManager {

    private FilesManager filesManager;
    private static final String usersFilePath = "data";
    private static final String usersFilename = "myShadow.txt";
    private Path usersFile;

    /**
     * Constructor de UsersManager.
     * Inicializa el gestor de archivos y verifica la existencia del directorio
     * y archivo de usuarios, creándolos con permisos restringidos si no existen.
     *
     * @throws IOException Si ocurre un error al crear el directorio o archivo
     */
    public UsersManager() throws IOException {

        filesManager = new FilesManager();

        // Chequeamos si existe ya el directorio para los datos
        Path usersDir = Path.of(usersFilePath);
        if (Files.notExists(usersDir)) {
            // Si no existe se crea con los permisos necesarios
            filesManager.createPrivilegedDir(usersDir);
        }

        // Chequeamos si existe ya el fichero de usuarios
        usersFile = Path.of(usersFilePath + "/" + usersFilename);
        if (Files.notExists(usersFile)) {
            // Si no existe se crea con permisos restringidos
            filesManager.createPrivilegedFile(usersDir, usersFilename);
        }

    }

    /**
     * Registra un nuevo usuario en el sistema.
     * Verifica que el usuario no exista previamente, genera un salt aleatorio,
     * calcula el hash de la contraseña mediante PBKDF2 y almacena los datos
     * en el archivo myShadow.txt con el formato: username:rol:salt:hash
     *
     * @param username Nombre de usuario (debe ser único)
     * @param password Contraseña en array de caracteres (se limpiará al finalizar)
     * @param role     Rol del usuario (USER o ADMIN)
     * @return true si el registro fue exitoso
     * @throws Exception Si el usuario ya existe o ocurre un error en el hash
     */
    public boolean registerUser(String username, char[] password, Roles role) throws Exception {

        // Verificar si el usuario ya existe
        if (!searchUser(username).isEmpty()) {
            throw new Exception("El usuario ya existe");
        }

        // Prevenir inyección de separadores en el username
        if (username.contains(":") || username.contains("\n") || username.contains("\r")) {
            throw new Exception("El nombre de usuario contiene caracteres no permitidos");
        }

        // Generar salt y hash de la contraseña
        byte[] salt = PasswordHasher.generateSalt();
        String hash = PasswordHasher.hashPassword(password, salt);

        // Codificar salt en Base64 para almacenamiento
        String saltBase64 = Base64.getEncoder().encodeToString(salt);

        // Formato: username:rol:salt:hash
        String userLine = username + ":" + role.getRole() + ":" + saltBase64 + ":" + hash + System.lineSeparator();

        // Escribir en el archivo
        Files.writeString(usersFile, userLine, StandardOpenOption.APPEND);

        return true;

    }

    /**
     * Busca un usuario en el archivo myShadow.txt.
     * Realiza una búsqueda lineal sobre el archivo para encontrar
     * la línea correspondiente al nombre de usuario especificado.
     *
     * @param username Nombre de usuario a buscar
     * @return Línea completa del usuario en formato "username:rol:salt:hash"
     *         o cadena vacía si no existe
     */
    private String searchUser(String username) {

        if (!Files.exists(usersFile)) {
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(usersFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Verifica si la contraseña proporcionada corresponde al usuario.
     * Recupera el salt almacenado, genera el hash con la contraseña de prueba
     * y compara con el hash almacenado en el archivo.
     *
     * @param username       Nombre de usuario a autenticar
     * @param trialPassword Contraseña proporcionada (se limpiará al finalizar)
     * @return true si la contraseña es correcta, false en caso contrario
     * @throws Exception Si ocurre un error al generar el hash
     */
    public boolean checkUserPassword(String username, char[] trialPassword) throws Exception {

        boolean userAuthenticated = false;

        String userLine = searchUser(username);

        if (!userLine.isEmpty()) {
            String[] parts = userLine.split(":");

            if (parts.length >= 4) {
                // parts[0] = username, parts[1] = role, parts[2] = saltBase64, parts[3] = hash
                String saltBase64 = parts[2];
                String storedHash = parts[3];

                // Decodificar salt de Base64
                byte[] salt = Base64.getDecoder().decode(saltBase64);

                // Generar hash con la contraseña proporcionada
                String trialHash = PasswordHasher.hashPassword(trialPassword, salt);

                // Comparar hashes
                userAuthenticated = storedHash.equals(trialHash);
            }
        }

        return userAuthenticated;

    }

    /**
     * Obtiene el rol de un usuario a partir de su nombre.
     *
     * @param username Nombre del usuario
     * @return Rol del usuario (USER o ADMIN)
     * @throws Exception Si el usuario no existe o el formato del archivo es incorrecto
     */
    public Roles getRole(String username) throws Exception {

        String userRole = searchUser(username).split(":")[1];
        return Roles.getRole(userRole);

    }

}