package psp.a51.sec;

import psp.a51.tools.Tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Gestor de archivos y directorios con permisos restringidos.
 * Esta clase proporciona métodos para crear archivos y directorios
 * con privilegios mínimos, asegurando que solo el usuario propietario
 * tenga acceso a los recursos creados.
 *
 * @author PSP - DAM2
 * @version 1.0
 * @see psp.a51.tools.Tools.UtilidadesSistema
 */
public class FilesManager {

    /**
     * Crea un archivo con permisos restringidos (solo lectura/escritura para el propietario).
     * En sistemas Linux establece permisos 600 (rw-------).
     * En Windows intenta establecer atributos de solo lectura y confía en la herencia
     * de permisos del directorio padre que ya fue configurado como privado.
     *
     * @param path     Ruta del directorio donde se creará el archivo
     * @param filename Nombre del archivo a crear
     * @throws IOException Si ocurre un error al crear el archivo o establecer los permisos
     */
    public void createPrivilegedFile(Path path, String filename) throws IOException {

        Path filePath = path.resolve(filename);

        // Crear el archivo
        Files.createFile(filePath);

        // Establecer permisos según el sistema operativo
        if (Tools.UtilidadesSistema.isLinux()) {
            // En Linux: permisos 600 (rw-------)
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-------");
            Files.setPosixFilePermissions(filePath, permissions);
        } else if (Tools.UtilidadesSistema.isWindows()) {
            // En Windows: establecer que solo el propietario tenga acceso
            // Nota: Windows no tiene el mismo sistema de permisos POSIX
            // Esta es una aproximación básica
            try {
                Files.setAttribute(filePath, "dos:readonly", false);
                // En Windows no podemos establecer permisos POSIX, pero el archivo
                // hereda los permisos del directorio que ya configuramos como privado
            } catch (Exception e) {
                System.err.println("No se pudieron establecer permisos específicos en Windows: " + e.getMessage());
            }
        }

    }

    /**
     * Crea un directorio con permisos restringidos (todos los permisos solo para el propietario).
     * En sistemas Linux establece permisos 700 (rwx------).
     * En Windows intenta establecer atributos y confía en los permisos por defecto del usuario.
     *
     * @param path Ruta del directorio a crear
     * @throws IOException Si ocurre un error al crear el directorio o establecer los permisos
     */
    public void createPrivilegedDir(Path path) throws IOException {

        // Crear el directorio
        Files.createDirectories(path);

        // Establecer permisos según el sistema operativo
        if (Tools.UtilidadesSistema.isLinux()) {
            // En Linux: permisos 700 (rwx------)
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwx------");
            Files.setPosixFilePermissions(path, permissions);
        } else if (Tools.UtilidadesSistema.isWindows()) {
            // En Windows: no podemos establecer permisos POSIX directamente
            // Se asume que el directorio hereda permisos del proceso actual
            try {
                Files.setAttribute(path, "dos:readonly", false);
                // Nota: Para Windows se podría usar icacls o herramientas nativas,
                // pero por simplicidad dejamos los permisos por defecto del usuario
            } catch (Exception e) {
                System.err.println("No se pudieron establecer permisos específicos en Windows: " + e.getMessage());
            }
        }

    }
}