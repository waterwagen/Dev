package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class SpikeSandbox
{
	// add two numbers algorithm: 
	//								'&' then '<< 1' handles the bits which are the same in the two numbers, 
	//								what about the bits that are different?
	
//	private int bitwiseAddition(int first, int second)
//	{
//		int common_bits = first & second;
//		int different_bits = first ^ second;
//		int common_bits_doubled = common_bits << 1; // double the common bits because they were in both numbers; we do this by shifting all the bits to the left one place
//		
//		int result = different_bits;
//		int bits_to_add = common_bits_doubled;
//		while(true)
//		{
//			if((result & bits_to_add) == 0) // if there are no bits which are set in both numbers, then it's safe to or them together and return the result
//				return result | bits_to_add;
//			int orig_result = result;
//			result = result ^ bits_to_add;
//			bits_to_add = orig_result & bits_to_add;
//			bits_to_add = bits_to_add << 1;
//		}
//	}
	
	private int bitwiseAddition(int first, int second)
	{
		int exclusive_bits = first ^ second;  // these are the bits which are only set in one of the numbers
		int carry_bits = first & second;  // these are the bits which are set in both numbers
		
		carry_bits <<= 1;  // carry the carry bits by shifting them left one place
		int result = exclusive_bits;  // initialize the result to the value of the exclusive bits since they need no further processing
		while((result & carry_bits) != 0) // if after carrying the carry bits there are still bits which are set in both numbers, then set any exclusive bits on the result and shift the rest of the remaining bits left before trying this test again
		{
			int orig_result = result;
			result = result ^ carry_bits;  // set the bits which are only in the shifted carry bits on the result because they are safe to add
			carry_bits = (orig_result & carry_bits) << 1; // set the carry bits to be only the current bits which are in the result also, now that we've modified the result
		}
		return result | carry_bits;
	}
	
	@Test
	public void test()
	{
		assertBitwiseAdditionEqualsPlusAddition(0, 0);
		assertBitwiseAdditionEqualsPlusAddition(7, 14);
		assertBitwiseAdditionEqualsPlusAddition(22, 38);
		assertBitwiseAdditionEqualsPlusAddition(22, 46);
		assertBitwiseAdditionEqualsPlusAddition(401, 593);
		assertBitwiseAdditionEqualsPlusAddition(153252, 36346);
		assertBitwiseAdditionEqualsPlusAddition(32454236, 23263426);
		
		assertEqual(10 + 10, 10 << 1);
		assertEqual(2 + 4, 2 | 4);

		int fir = 31;
		int sec = 7;
		int diff_bits = fir ^ sec;
		assertEqual(diff_bits, 24);
		int same_bits = fir & sec;
		assertEqual(same_bits, 7);
		assertEqual(diff_bits + (same_bits << 1), 38);
		
		assertEqual(2 | 2, 2);
		assertEqual(2 ^ 2, 0);
		assertEqual(2 & 2, 2);
//		assertEqual(~Integer.MAX_VALUE, 0);
		
		assertEqual(1 | 2, 3);
		assertEqual(1 ^ 2, 3);
		assertEqual(1 & 2, 0);
		
		assertEqual(1 | 3, 3);
		assertEqual(1 ^ 3, 2);
		assertEqual(1 & 3, 1);
		
		assertEqual(15 | 10, 15);
		assertEqual(15 ^ 10, 5);
		assertEqual(15 & 10, 10);
		
		assertEqual(7 | 14, 15);
		assertEqual(7 ^ 14, 9);
		assertEqual(7 & 14, 6);
		
		assertEqual(2 << 1, 4);
		assertEqual(2 << 2, 8);
		assertEqual(2 >> 1, 1);
		assertEqual(2 >> 2, 0);
		
		assertEqual(3 << 1, 6);
		assertEqual(3 << 2, 12);
	}

	private void assertBitwiseAdditionEqualsPlusAddition(int first, int second)
	{
		assertEqual(first + second, bitwiseAddition(first,second));
	}
	
	private void assertEqual(int expected, int result)
	{
		assertThat(result, is(equalTo(expected)));
	}
}
