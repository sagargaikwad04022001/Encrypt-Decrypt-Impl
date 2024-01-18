import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionDecryption {

    public static void main(String[] args) throws Exception {
        String originalMessage="Hello Champ";
        SecretKey secretKey=generateSecretKey();
        String encryptedMessage=encrypt(originalMessage,secretKey);
        System.out.println(encryptedMessage);
        String decryptedMessage=decrypt(encryptedMessage,secretKey);
        System.out.println(decryptedMessage);

    }
    private  static SecretKey generateSecretKey() throws  Exception{
        KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }
    private static String encrypt(String originalMessage,SecretKey secretKey) throws Exception{
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] encryBytes=cipher.doFinal(originalMessage.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryBytes);
    }
    private static String decrypt(String encryData,SecretKey secretKey) throws  Exception
    {
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        byte[]decr=cipher.doFinal(Base64.getDecoder().decode(encryData));
        return new String(decr,StandardCharsets.UTF_8);
    }
}
