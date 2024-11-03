
package CRUDs;

import java.security.MessageDigest;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class Login {
    private static final Logger LOG = Logger.getLogger(Login.class.getName());
    
    public static String sha1(String message) {
        try{
            return encrypt(message, "SHA1");
        }catch(NullPointerException e){
            return null;
        }
    }
    
    public static String md5(String message) {
        return encrypt(message, "MD5");
    }
    
    private static String encrypt(String message, String type){
        try {
            MessageDigest md;
            byte[] buffer, digest;
            StringBuilder hash = new StringBuilder();
            try {
                buffer = message.getBytes("UTF-8");
                md = MessageDigest.getInstance(type);
            }catch(Exception e) {
                throw new RuntimeException(e);
            }
            md.update(buffer);
            digest = md.digest();
            for (byte b:digest) {
                hash.append(String.format("%02x", b & 0xff));
            }
            return hash.toString();
        }catch(NullPointerException e){
            return null;
        }
    }
 public static byte[] cifra(String sinCifrar) throws Exception {
	final byte[] bytes = sinCifrar.getBytes("UTF-8");
	final Cipher aes = obtieneCipher(true);
	final byte[] cifrado = aes.doFinal(bytes);
	return cifrado;
}

public static String descifra(byte[] cifrado) throws Exception {
	final Cipher aes = obtieneCipher(false);
	final byte[] bytes = aes.doFinal(cifrado);
	final String sinCifrar = new String(bytes, "UTF-8");
	return sinCifrar;
}

private static Cipher obtieneCipher(boolean paraCifrar) throws Exception {
	final String frase = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
	final MessageDigest digest = MessageDigest.getInstance("SHA");
	digest.update(frase.getBytes("UTF-8"));
	final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

	final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
	if (paraCifrar) {
		aes.init(Cipher.ENCRYPT_MODE, key);
	} else {
		aes.init(Cipher.DECRYPT_MODE, key);
	}

	return aes;
}   
    
    
}
