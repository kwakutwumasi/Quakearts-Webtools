package com.quakearts.test.exceptionhandler;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCollision {

	@Test
	public void test() {
		long operand1 = "A string".hashCode(), operand2 = "Another string".hashCode();
		
		long hash = operand1 * 5 + operand2 *7;
		
		long collisionHash = 0;
		long i=1, j=1;
		for(; i< Integer.MAX_VALUE; i++){
			for(; j < Integer.MAX_VALUE; j++){
				collisionHash = i * 5 + j * 7;
				if(hash == collisionHash)
					fail("Collision for "+i+" and "+j);
				
				if(collisionHash > Integer.MAX_VALUE)
					break;
			}
		}
		
		System.out.println("Finished with "+i+" "+j);
	}

}
