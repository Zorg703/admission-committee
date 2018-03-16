package by.mordas.project.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    private static final String SECRET_KEY="1234SECURITY@key";

    public static String encodePassword(String password){
        StringBuilder secretPassword= new StringBuilder();
        try {
            Cipher cipher =Cipher.getInstance("AES");
            SecretKeySpec key=new SecretKeySpec(SECRET_KEY.getBytes(),"AES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] code=cipher.doFinal(password.getBytes());
            for (byte b:code) {
                secretPassword.append(b);
            }
        } catch (Exception e) {
           //TODO
        }
        return secretPassword.toString();
    }

}
