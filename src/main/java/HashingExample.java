import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashingExample {
    public static void main(String[] args) throws Exception {
        String originalmsg="Hello Sabir";
        String hashedMsg=hash256(originalmsg);
        System.out.println("Original Msg:"+originalmsg);
        System.out.println("Hashed Msg:"+hashedMsg);

    }
    private static String hash256(String data) throws Exception
    {
        MessageDigest messageDigest=MessageDigest.getInstance("SHA");
        byte[] hashedbytes=messageDigest.digest(data.getBytes(StandardCharsets.UTF_8))  ;
        StringBuilder stringBuilder=new StringBuilder();
        for (byte hashedByte:hashedbytes)
        {
            String hex=Integer.toHexString(0xff & hashedByte);
            if(hex.length()==1)
            {
                stringBuilder.append('0');
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }
}
