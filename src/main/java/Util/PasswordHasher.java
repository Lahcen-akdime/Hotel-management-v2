package Util;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64 ;

public class PasswordHasher {

    public static String HashPassword(String password , String salt){

        try {
        KeySpec spec = new PBEKeySpec(password.toCharArray(),salt.getBytes(),65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1") ;
        byte[] hash = factory.generateSecret(spec).getEncoded() ;
        return Base64.getEncoder().encodeToString(hash)  ;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeySpecException e){
            System.out.println(e.getMessage());
        }
        return null ;

    }
}
