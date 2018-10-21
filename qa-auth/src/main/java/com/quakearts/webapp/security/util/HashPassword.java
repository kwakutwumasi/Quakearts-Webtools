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
package com.quakearts.webapp.security.util;

import java.security.*;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashPassword {

	private static final Logger log = Logger.getLogger(HashPassword.class.getName());
	private String plainText;
	private String algorithm = "MD5";
	private String salt;
	private int iterations;
	public static final String DEFAULT_SALT = "&Sds$g1Art412Jj}|me?*0";
	private MessageDigest mdAlgorithm;

	public HashPassword() {
	}

	public HashPassword(String plainText, String algorithm, int iterations, String salt) {
		this.plainText = plainText;
		this.algorithm = algorithm;
		this.iterations = iterations;
		this.salt = salt;
	}

	public byte[] toBytes() {
		byte[] hashBytes;
		try {
			this.mdAlgorithm = MessageDigest.getInstance(this.algorithm);
			String hashText = this.plainText + (this.salt != null ? this.salt : DEFAULT_SALT);
			this.mdAlgorithm.update(hashText.getBytes());
			hashBytes = this.mdAlgorithm.digest();
			for (int i = 0; i < this.iterations; i++) {
				this.mdAlgorithm.update(hashBytes);
				hashBytes = this.mdAlgorithm.digest();
			}
		} catch (NoSuchAlgorithmException nsae) {
			log.log(Level.SEVERE, nsae.getLocalizedMessage(), nsae);
			return new byte[0];
		}
		return hashBytes;
	}

	public String toString() {
		String hashText = null;
		try {
			mdAlgorithm = MessageDigest.getInstance(algorithm);
			hashText = getHashText(this.plainText + (salt != null ? salt : DEFAULT_SALT), mdAlgorithm);
			for (int i = 0; i < iterations; i++) {
				hashText = getHashText(hashText, mdAlgorithm);
			}
		} catch (NoSuchAlgorithmException nsae) {
			log.log(Level.SEVERE, nsae.getLocalizedMessage(), nsae);
			return "";
		}
		return hashText;
	}

	private String getHashText(String plainText, MessageDigest mdAlgorithm) {
		mdAlgorithm.update(plainText.getBytes());
		byte[] digest = mdAlgorithm.digest();
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			String hex = Integer.toHexString(0xFF & digest[i]);
			if (hex.length() < 2) {
				hexString.append("0");
			}
			hexString.append(hex);
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

	public static void main(String[] args) {
		String alg = null;
		String salt = null;
		int iter = 0;
		if (args.length < 1) {
			usage();
			return;
		}
		
		if(((args.length - 1) % 2) == 1) {
			usage();
			return;
		}

		for (int i = 1; i < args.length; i += 2) {
			if (args[i].equals("-alg")) {
				alg = args[i + 1];
			} else if (args[i].equals("-salt")) {
				salt = args[i + 1];
			} else if (args[i].equals("-iter")) {
				try {
					iter = Integer.parseInt(args[i + 1]);
				} catch (Exception ex) {
					log.info("Invalid integer");
					usage();
					return;
				}
			} else {
				String arg = args[i];
				log.info(() -> MessageFormat.format("Invalid argument: {0}", arg));
				usage();
				return;
			}
		}
		
		if (alg == null)
			alg = "MD5";

		HashPassword hash = new HashPassword(args[0], alg, iter, salt);
		String output = hash.toString();

		log.info(()->MessageFormat.format("Hash: {0}",output));
	}

	private static void usage() {
		log.info("Usage - hash hashstring [-alg algorithim] [-salt salt] [-iter iterations]");
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
