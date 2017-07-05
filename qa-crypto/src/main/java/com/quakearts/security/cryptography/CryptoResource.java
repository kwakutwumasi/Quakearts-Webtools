/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.security.cryptography;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
import com.quakearts.security.cryptography.permission.CrytographyOperationPermission;

public class CryptoResource {
    private Key secretKey;
    private String cipherInstance;
    private CrytographyOperationPermission decryptPermission, encryptPermission;
    
    public CryptoResource(Key key, String instance, String name) throws NoSuchAlgorithmException, NoSuchPaddingException 
    {
        secretKey = key;    
        cipherInstance = instance;
        if(System.getSecurityManager()!=null){
        	decryptPermission = new CrytographyOperationPermission(name, "decrypt");        
        	encryptPermission = new CrytographyOperationPermission(name, "encrypt");
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
    	SecurityManager manager = System.getSecurityManager();
    	if(manager != null){
    		manager.checkPermission(decryptPermission);
    	}

    	Cipher cipher;
        try {
            cipher = Cipher.getInstance(cipherInstance);
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalCryptoActionException(nsae);
        } catch (NoSuchPaddingException nspe) {
            throw new IllegalCryptoActionException(nspe);
        }

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
    	SecurityManager manager = System.getSecurityManager();
    	if(manager != null){
    		manager.checkPermission(encryptPermission);
    	}

    	Cipher cipher;
        try {
            cipher = Cipher.getInstance(cipherInstance);
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalCryptoActionException(nsae);
        } catch (NoSuchPaddingException nspe) {
            throw new IllegalCryptoActionException(nspe);
        }

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
