package psp.a51.sec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {

    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    public static String hashPassword(char[] password, byte[] salt) throws Exception {
        int iterations = 100_000;
        int keyLength = 256; // La clave derivada tendrá 256 bits que no equivale a usar SHA256

        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }
/**
    public static void main(String[] args) throws Exception {
        for (int i=0; i<3; i++) {
            byte[] salt = generateSalt();
            String hash = hashPassword("secreta123".toCharArray(), salt);

            System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
            System.out.println("Hash: " + hash);
        }
    }
 */
}
