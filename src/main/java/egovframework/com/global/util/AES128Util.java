package egovframework.com.global.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64; 
import java.util.Base64.Decoder; 
import java.util.Base64.Encoder;

 
@SuppressWarnings("restriction")
public class AES128Util {
    /**
     * 암호화
     *
     * @param input
     * @param key
     * @return
     */
    @SuppressWarnings("restriction")
	public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {
 
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
 
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch(Exception e) {
            System.out.println(e.toString());
        }
 
        Encoder encoder = Base64.getEncoder();
 
        byte[] encodedBytes = encoder.encode(crypted);
        
        return new String(encodedBytes);
    }
 
    /**
     * 복호화
     *
     * @param input
     * @param key
     * @return
     */
    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
        	Decoder decoder = Base64.getDecoder(); 
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
 
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            
            output = cipher.doFinal(decoder.decode(input));
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return new String(output);
    }
}


