package com.quakearts.security.cryptography.test;

import static org.junit.Assert.*;

import org.junit.Rule;

import static org.hamcrest.core.Is.*;
import static com.quakearts.security.cryptography.utils.BitManipulationUtilities.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BitManipulationUtilitiesTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testCircularBitShiftLeft() {
		assertThat(convert(circularBitShiftLeft((byte)2, 0)), is("00000010"));
		assertThat(convert(circularBitShiftLeft((byte)2, 3)), is("00010000"));
		assertThat(convert(circularBitShiftLeft((byte)-2, 3)), is("11110111"));
	}
	
	@Test
	public void testCircularBitShiftLeftCountLessThan0() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(is("Count must be greater than or equal to 0"));
		assertThat(convert(circularBitShiftLeft((byte)2, -1)), is("00000010"));
	}
	
	@Test
	public void testCircularBitShiftLeftCountGreaterThan7() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(is("Count must be less than 7"));
		assertThat(convert(circularBitShiftLeft((byte)2, 8)), is("00000010"));
	}

	@Test
	public void testCircularBitShiftRight() {
		assertThat(convert(circularBitShiftRight((byte)2, 0)), is("00000010"));
		assertThat(convert(circularBitShiftRight((byte)2, 3)), is("01000000"));
		assertThat(convert(circularBitShiftRight((byte)-2, 3)), is("11011111"));
	}
	
	@Test
	public void testCircularBitShiftRightCountLessThan0() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(is("Count must be greater than or equal to 0"));
		assertThat(convert(circularBitShiftRight((byte)2, -1)), is("00000010"));
	}
	
	@Test
	public void testCircularBitShiftRightCountGreaterThan7() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(is("Count must be less than 7"));
		assertThat(convert(circularBitShiftRight((byte)2, 8)), is("00000010"));
	}

}
