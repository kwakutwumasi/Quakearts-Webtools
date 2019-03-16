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
package com.quakearts.security.cryptography.utils;

import org.jboss.weld.exceptions.IllegalArgumentException;

public class BitManipulationUtilities {

	private BitManipulationUtilities() {}
	
	public static byte circularBitShiftLeft(byte b, int count) {
		if (count < 0)
			throw new IllegalArgumentException("Count must be greater than or equal to 0");

		if (count > 7)
			throw new IllegalArgumentException("Count must be less than 7");

		if (count == 0)
			return b;

		if (b >= 0)
			return (byte) ((b << count) | (b >>> Byte.SIZE - count));
		else {
			return (byte) ((b) << count | (0x7f & b) >>> Byte.SIZE - count | (0x40 >>> (Byte.SIZE
					- count - 1)));
		}
	}

	public static byte circularBitShiftRight(byte b, int count) {
		if (count < 0)
			throw new IllegalArgumentException("Count must be greater than or equal to 0");

		if (count > 7)
			throw new IllegalArgumentException("Count must be less than 7");

		if (count == 0)
			return b;

		if (b >= 0)
			return (byte) ((b >>> count) | (b << Byte.SIZE - count));
		else {
			return (byte) ((0x7f & b) >>> count | b << Byte.SIZE - count | (0x40 >>> count - 1));
		}
	}

	public static String convert(byte a) {
		if(a>=0){
			String bin =Integer.toBinaryString(a);
			StringBuilder builder = new StringBuilder();
			for(int i=0;i<8-bin.length();i++)
				builder.append("0");
			return builder.append(bin).toString();
		} else {
			return Integer.toBinaryString(a).substring(24);
		}
	}
}
