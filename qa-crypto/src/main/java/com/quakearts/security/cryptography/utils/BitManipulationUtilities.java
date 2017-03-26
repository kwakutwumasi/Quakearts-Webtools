package com.quakearts.security.cryptography.utils;
public class BitManipulationUtilities {

	public static int[] getSeries(int pin) {
		if (pin == 0)
			return new int[] { 0 };

		if (pin < 0)
			pin *= -1;

		int length = 0;
		int c = pin;

		while (c > 0) {
			c = c / 10;
			length++;
		}

		int[] flipped = new int[length * 4], series = new int[length * 4];
		int t = 10;
		int i = 0, k = 0;
		while (k < length) {
			flipped[i] = (byte) (((pin % t) * 10) / t);
			if (flipped[i] > 7) {
				flipped[i + 1] = (byte) (flipped[i] - 7);
				flipped[i++] = 7;
			}
			i++;
			k++;
			t *= 10;
		}

		for (int p = i - 1, j = 0; p >= 0; p--, j++) {
			series[j] = flipped[p];
		}

		for (int p = i; i < series.length; i++)
			series[i] = series[i - p];

		return series;
	}

	public static byte circularBitShiftLeft(byte b, int count) {
		if (count < 0)
			count = count * -1;

		if (count > 7)
			count = count % 8;

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
			count = count * -1;

		if (count > 7)
			count = count % 8;

		if (count == 0)
			return b;

		if (b >= 0)
			return (byte) ((b >>> count) | (b << Byte.SIZE - count));
		else {
			return (byte) ((0x7f & b) >>> count | b << Byte.SIZE - count | (0x40 >>> count - 1));
		}
	}
	
	public static String arrayToString(byte[] bites){
		boolean first = true;
		StringBuilder output = new StringBuilder("[");
		for(byte bite:bites){
			output.append((first?"":",")+convert(bite));
			if(first)
				first = false;
		}
		return output.append("]").toString();
	}

	public static String arrayToString(int[] bites){
		boolean first = true;
		StringBuilder output = new StringBuilder("[");
		for(int bite:bites){
			output.append((first?"":",")+bite);
			if(first)
				first = false;
		}
		return output.append("]").toString();
	}
	
	public static String convert(byte a) {
		if(a>=0){
			String bin =Integer.toBinaryString(a);
			StringBuilder builder = new StringBuilder();
			for(int i=0;i<8-bin.length();i++)
				builder.append("0");
			return builder.append(bin).toString();
		} else
			return Integer.toBinaryString(a).substring(24);
	}
	
	public static int[] toArray(long value){
		int topDec = 10;
		int length = 1;
		while(value>topDec){
			topDec*=10;
			length++;
		}
		
		int[] intArray = new int[length];
		topDec = topDec/10;
		long remainder = value;
		for(int i=0;i<length;i++){
			intArray[i] = (int) ((remainder - (remainder % topDec))/topDec);
			remainder = remainder % topDec;
			topDec = topDec/10;
		}
		return intArray;
	}

}
