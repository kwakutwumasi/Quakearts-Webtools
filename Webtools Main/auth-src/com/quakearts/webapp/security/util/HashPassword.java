package com.quakearts.webapp.security.util;

import java.security.*;

public class HashPassword {

	private String plainText, algorithm="MD5", salt;
	private int iterations=0;
	public static final String DEFAULT_SALT = "&Sds$g1Art412Jj}|me?*0";
    private MessageDigest mdAlgorithm;

	
    public HashPassword() {
    }
    
    public HashPassword(String plainText,String algorithm,int iterations,String salt){
        this.plainText = plainText;
        this.algorithm = algorithm;
        this.iterations = iterations;
        this.salt = salt;
    }

	public byte[] toBytes() {
		byte[] hashBytes;
		try {
			this.mdAlgorithm = MessageDigest.getInstance(this.algorithm);
			String hashText = this.plainText
					+ (this.salt != null ? this.salt : DEFAULT_SALT);
			this.mdAlgorithm.update(hashText.getBytes());
			hashBytes = this.mdAlgorithm.digest();
			for (int i = 0; i < this.iterations; i++) {
				this.mdAlgorithm.update(hashBytes);
				hashBytes = this.mdAlgorithm.digest();
			}
		} catch (java.security.NoSuchAlgorithmException nsae) {
			System.err.println(nsae.getLocalizedMessage());
			return new byte[0];
		}
		return hashBytes;
	}
    
    public String toString() {
        String hashText = null;
        try {
        	mdAlgorithm = MessageDigest.getInstance(algorithm);
            hashText = getHashText(this.plainText+(salt!=null?salt:DEFAULT_SALT), mdAlgorithm);
            for(int i=0;i<iterations;i++){
            	hashText = getHashText(hashText,mdAlgorithm);
            }
        }
        catch (NoSuchAlgorithmException nsae) {
            System.err.println(nsae.getLocalizedMessage());
            return "";
        }
        return hashText;
    }
    
    private String getHashText(String plainText, MessageDigest mdAlgorithm) {

        mdAlgorithm.update(plainText.getBytes());

        byte[] digest = mdAlgorithm.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < digest.length; i++) {
            plainText = Integer.toHexString(0xFF & digest[i]);

            if (plainText.length() < 2) {
                plainText = "0" + plainText;
            }
            hexString.append(plainText);
        }

        return hexString.toString();
    }

    public String getAlgorithm() {
        return algorithm;
    }
    public String getPlainText() {
        return plainText;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public static void main(String[] args){
    	String alg=null,salt=null;
    	int iter=0;
        if(args.length<1 || ((args.length-1)%2)==1){
        	usage();
        }
        
        for(int i=1;i<args.length;i+=2){
        	if(args[i].equals("-alg")){
        		alg = args[i+1];
        	}else if(args[i].equals("-salt")){
        		salt = args[i+1];
        		if(salt.equals("DEF"))
        			salt = DEFAULT_SALT;
        	}else if(args[i].equals("-iter")){
        		try{
        			iter = Integer.parseInt(args[i+1]);
        		}catch(Exception ex){
        			System.out.println("Invalid integer");
        			usage();
        		}
        	}else{
        		System.out.println("Invalid argument: "+args[i]);
        		usage();
        	}
        }
        if(alg == null)
        	alg="MD5";
        
    	HashPassword hash = new HashPassword(args[0],alg,iter,salt);
    	String output = hash.toString();
    	
        System.out.println("Hash: ");
        System.out.println(output);
    }
    
    private static void usage(){
        System.out.println("Usage - hash hashstring [-alg algorithim] [-salt salt] [-iter iterations]");
        System.exit(0);
    }

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return salt;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getIterations() {
		return iterations;
	}
}

