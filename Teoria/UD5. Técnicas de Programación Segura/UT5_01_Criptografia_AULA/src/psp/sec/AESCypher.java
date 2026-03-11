package psp.sec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESCypher {

    public static class EncrytedData {
        private byte[] iv;
        private byte[] cipherText;
    }

    public static EncrytedData encrypt (String plainText, SecretKey key) throws Exception {

        byte[] iv = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);

        byte[] cipherText = cipher.doFinal(plainText.getBytes());

        EncrytedData data = new EncrytedData();
        data.iv = iv;
        data.cipherText = cipherText;

        return data;
    }






    public static String decrypt (EncrytedData data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, data.iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        byte[] plainText = cipher.doFinal(data.cipherText);
        return new String(plainText);
    }

    public static void main(String[] args) throws Exception {
        SecretKey key = KeyGenerator.getInstance("AES").generateKey();

        EncrytedData encrytedData = encrypt("Mensaje secreto", key);
        System.out.println("Clave de cifrado: " + Base64.getEncoder().encodeToString(key.getEncoded()));
        System.out.println("Vector de inicializacion: " + Base64.getEncoder().encodeToString(encrytedData.iv));
        System.out.println("Mensaje cifrado: " + Base64.getEncoder().encodeToString(encrytedData.cipherText));

        String decryptedData = decrypt(encrytedData, key);
        System.out.println(decryptedData);


        EncrytedData datosCifrados = new EncrytedData();
        datosCifrados.cipherText = Base64.getDecoder().decode("vlWAKV9lMl8IU4RUnwcHxZypzANitf+4gjQ5AbjygA==");
        datosCifrados.iv = Base64.getDecoder().decode("DAMmqZJDx2Cc4IFL");
        SecretKey key2 = new SecretKeySpec(Base64.getDecoder().decode("W/QhfYg528AVFO8y4GfX7Erv4e5aEX6KVK28fYxF4vk="), "AES");
        /**
        SecretKey key2 = new SecretKey() {
            @Override
            public String getAlgorithm() {
                return "AES";
            }

            @Override
            public String getFormat() {
                return "";
            }

            @Override
            public byte[] getEncoded() {
                return new String("7p+fNzeBboFJOsP06nM03RNjD21nyC6qDrhzJT9F0rQ=").getBytes();
            }
        };
         **/
        String datosDescifrados = decrypt(datosCifrados, key2);
        System.out.println(datosDescifrados);



    }
}