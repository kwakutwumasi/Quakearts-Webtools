package com.quakearts.security.cryptography;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class CryptoResource {
    private Cipher cipher;
    private Key secretKey;

    public CryptoResource(Key key, String instance) throws NoSuchAlgorithmException, NoSuchPaddingException 
    {
        try {
            cipher = Cipher.getInstance(instance);
            secretKey = key;
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        } catch (NoSuchPaddingException nspe) {
            nspe.printStackTrace();
        }
    }

    public static String byteAsHex(byte[] buf) {
    	if(buf==null)
    		return null;
    	
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
         if (((int) buf[i] & 0xff) < 0x10)
              strbuf.append("0");

         strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    public static byte[] hexAsByte (String hexstring) throws Exception{
        if(hexstring==null)
        	return null;
    	
        if(hexstring.length() % 2 !=0){
            throw new Exception("Invalid hexidecimal string.");
        }
        byte[] results = new byte[hexstring.length()/2];
        try{
            for(int i = 0; i < hexstring.length()-1; i+=2){
            	results[i/2]=((byte)Long.parseLong(hexstring.substring(i,i+2), 16));
            }
        }catch(NumberFormatException e){
        	System.err.println(e.getMessage());
        }
        
        return results;
    }

   
    public String doDecrypt(String cipheredtext) throws IllegalCryptoActionException {
        if(cipheredtext == null)
        	return null;

        String plainString = null;
        try{
            plainString = new String(doDecrypt(hexAsByte(cipheredtext)));
        }catch(Exception e){
            throw new IllegalCryptoActionException("Error decrypting text.\nException " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
        }
        return plainString;
    }

    public String doEncrypt(String plaintext) throws IllegalCryptoActionException {
        if(plaintext == null)
        	return null;
        	
        String cipheredString = byteAsHex(doEncrypt(plaintext.getBytes()));
        return cipheredString;
    }

    public synchronized byte[] doDecrypt(byte[] cipheredtext) throws IllegalCryptoActionException {
    	if(cipheredtext == null)
    		return null;
    	
        try {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                return cipher.doFinal(cipheredtext);
        } catch (Exception e) {
            throw new IllegalCryptoActionException("Error decrypting text.\nException " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
        }
    }

    public synchronized byte[] doEncrypt(byte[] plaintext) throws IllegalCryptoActionException {
		if(plaintext == null)
			return null;

        try {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                return cipher.doFinal(plaintext);
        } catch (Exception e) {
            throw new IllegalCryptoActionException("Cryptographic service failure.\n"+e.getMessage(),e.getCause());
        }
    }
}
