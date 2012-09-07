package com.waterwagen.parser;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.waterwagen.parser.IntegerParser;

public class TestIntegerParser
{
	IntegerParser mParser;
	
	@Before
	public void setupTest()
	{
		mParser = new IntegerParser();
	}
	
	@Test
	public void testSingleNumberAtomic()
	{
		verifySingleNumber(new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"});
		verifySingleNumber(new String[]{"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"});
		verifySingleNumber(new String[]{"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"});
	}
	
	@Test
	public void testSingleNumberNonAtomic()
	{
		verifySingleNumber(new String[]{"onehundred", 
										"onehundredone", 
										"onehundredtwo", 
										"fivethousandsixhundredeightynine", 
										"onebillionsixhundredseventyninemillionthreehundredfortyeightthousandninehundredtwentyseven"});
	}
	
	@Test
	public void testDoubleNumberAtomic()
	{
		verifyDoubleNumbers(new String[][]{
											{"one", "two"},
											{"threehundred", "fiftynine"},
											{"onebillionninehundredninetysixmillionsevenhundredfiftyninethousandtwohundredsixtyfive", "eighthundredninetythousand"}
											});
	}
	
	@Test
	public void testAmbiguousNumber()
	{
		verifySingleNumber(new String[]{"onebillioneight"});
	}
	
	/////////////////////// 
	/// Utility Methods ///
	///////////////////////
	
	private void verifyDoubleNumbers(String[][] test_vals)
	{
		for(String[] val : test_vals)
		{
			assertEquals("Unexpected parsed value.", Arrays.asList(val[0], val[1]), mParser.parse(val[0] + "green" + val[1] + "blue"));
			assertEquals("Unexpected parsed value.", Arrays.asList(val[0], val[1]), mParser.parse("green" + val[0] + "blue" + val[1] + "red"));
			assertEquals("Unexpected parsed value.", Arrays.asList(val[0], val[1]), mParser.parse("green" + val[0] + "blue" + val[1]));
		}
	}

	private void verifySingleNumber(String[] test_vals)
	{
		for(String val : test_vals)
		{
			assertEquals("Unexpected parsed value.", Arrays.asList(val), mParser.parse(val + "green"));
			assertEquals("Unexpected parsed value.", Arrays.asList(val), mParser.parse("green" + val + "blue"));
			assertEquals("Unexpected parsed value.", Arrays.asList(val), mParser.parse("green" + val));
		}
	}
}
