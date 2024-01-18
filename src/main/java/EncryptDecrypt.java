

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptDecrypt {

    public static void main(String[] args) throws Exception {
       String originalMessage="Hello everyone";
       SecretKey secretKey=generateSecretKey();
       String encryptedMessage=encrypt(originalMessage,secretKey);
       System.out.println(encryptedMessage);
       String decryptedMessage=decrypt(encryptedMessage,secretKey);
        System.out.println(decryptedMessage);

    }
    private static SecretKey generateSecretKey() throws  Exception{
        KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    private static String encrypt(String originalMessage,SecretKey secretKey) throws Exception
    {
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] encryptedBytes=cipher.doFinal(originalMessage.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String encryptedMessage,SecretKey secretKey) throws Exception
    {
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        byte[] decryptedData=cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedData,StandardCharsets.UTF_8);
    }
}
