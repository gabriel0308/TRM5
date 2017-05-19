/**
 * @author adauto
 */
package br.com.trm.utilitarios;
 
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class Criptografia {
    /**
     * Metodo de para criptografar.
     */
    public static String criptografar(String senha) {
		try {
			if(senha == null || senha.isEmpty()){
				senha = "@";
			}
			MessageDigest m = MessageDigest.getInstance("MD5");
			byte messageDigest[] = m.digest(senha.getBytes("UTF8"));
			StringBuilder d = new StringBuilder();
			for (byte b : messageDigest) {
				d.append(String.format("%02X", 0xff & b));
			}
			senha = d.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return senha;
    }
}