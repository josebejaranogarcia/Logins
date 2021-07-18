package FirstLogin;

import org.postgresql.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class EncrypterPass {

    private char pass[];
    //policy restriction use 32 bytes keys. AES only supports key sizes of 16, 24 or 32 bytes.
    private static String ENCRYPT_KEY = "clave-compartida-no-revelarnunca";
    private static final String ALGORITHM = "AES";

    static String encript(char[] pass) {
        try {
            Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), ALGORITHM);
           // System.out.println(aesKey.getFormat()+" | "+ aesKey.getEncoded());
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedPass = cipher.doFinal(String.valueOf(pass).getBytes());
            return Base64.encodeBytes(encryptedPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

     static String decrypt(String encrypted) throws Exception {
        byte[] encryptedBytes=Base64.decode( encrypted.replace("\n", "") );

        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        String decrypted = new String(cipher.doFinal(encryptedBytes));
        return decrypted;
    }


}
