package com.waterwagen.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.waterwagen.parser.IntegerTranslator;

public class TestIntegerTranslator
{
	private static final String[] SINGLE_DIGITS_NAMES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private static final String[] TEENS_NAMES = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	private static final String[] TENS_NAMES = {"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	
	private IntegerTranslator mTranslator;

	@Before
	public void setupTest()
	{
		mTranslator = new IntegerTranslator();
	}
	
	@Test
	public void testAtomicNumbers()
	{
		testSingleDigits();
		testTeens();
		testTens();
	}

	@Test
	public void testNonAtomic()
	{
		assertEquals("Unexpected number translation.", 28, mTranslator.translate("twentyeight").intValue());
		assertEquals("Unexpected number translation.", 34, mTranslator.translate("thirtyfour").intValue());
		assertEquals("Unexpected number translation.", 47, mTranslator.translate("fortyseven").intValue());
		assertEquals("Unexpected number translation.", 52, mTranslator.translate("fiftytwo").intValue());
		assertEquals("Unexpected number translation.", 69, mTranslator.translate("sixtynine").intValue());
		assertEquals("Unexpected number translation.", 71, mTranslator.translate("seventyone").intValue());
		assertEquals("Unexpected number translation.", 85, mTranslator.translate("eightyfive").intValue());
		assertEquals("Unexpected number translation.", 93, mTranslator.translate("ninetythree").intValue());
		assertEquals("Unexpected number translation.", 96, mTranslator.translate("ninetysix").intValue());
		assertEquals("Unexpected number translation.", 98, mTranslator.translate("ninetyeight").intValue());
		assertEquals("Unexpected number translation.", 100, mTranslator.translate("onehundred").intValue());
		assertEquals("Unexpected number translation.", 659, mTranslator.translate("sixhundredfiftynine").intValue());
		assertEquals("Unexpected number translation.", 1_000, mTranslator.translate("onethousand").intValue());
		assertEquals("Unexpected number translation.", 8_329, mTranslator.translate("eightthousandthreehundredtwentynine").intValue());
		assertEquals("Unexpected number translation.", 525_346, mTranslator.translate("fivehundredtwentyfivethousandthreehundredfortysix").intValue());
		assertEquals("Unexpected number translation.", 1_000_000, mTranslator.translate("onemillion").intValue());
		assertEquals("Unexpected number translation.", 1_000_934, mTranslator.translate("onemillionninehundredthirtyfour").intValue());
		assertEquals("Unexpected number translation.", 1_434_237, mTranslator.translate("onemillionfourhundredthirtyfourthousandtwohundredthirtyseven").intValue());
		assertEquals("Unexpected number translation.", 508_946_722, mTranslator.translate("fivehundredeightmillionninehundredfortysixthousandsevenhundredtwentytwo").intValue());
		assertEquals("Unexpected number translation.", 1_000_000_000, mTranslator.translate("onebillion").intValue());
		assertEquals("Unexpected number translation.", 1_874_563_923L, mTranslator.translate("onebillioneighthundredseventyfourmillionfivehundredsixtythreethousandninehundredtwentythree").intValue());
	}

	@Test
	public void testNonNumberThrowsException()
	{
		String[] to_translate = {"blah", "oneblah", "blahone", "blahoneblah", "oneblahmillion"};
		for(String str : to_translate)
			verifyTranslationException(str);
	}
	
	///////////////////////
	/// Utility Methods ///
	///////////////////////
	
	private void testSingleDigits()
	{
		for(int i = 0; i < 10; i++)
			assertEquals("Unexpected number translation.", i, mTranslator.translate(SINGLE_DIGITS_NAMES[i]).intValue());
	}

	private void testTeens()
	{
		int array_index = 0;
		for(int i = 10; i < 20; i++)
			assertEquals("Unexpected number translation.", i, mTranslator.translate(TEENS_NAMES[array_index++]).intValue());
	}

	private void testTens()
	{
		int array_index = 0; 
		for(int i = 10; i < 100; i += 10)
			assertEquals("Unexpected number translation.", i, mTranslator.translate(TENS_NAMES[array_index++]).intValue());
	}

	private void verifyTranslationException(String to_translate)
	{
		try
		{
			mTranslator.translate(to_translate).intValue();
		}
		catch (Exception exc)
		{
			assertTrue("Unexpected exception type thrown. Expected IllegalArgumentException but received " + exc.getClass().getName(),
						exc instanceof IllegalArgumentException);
			assertEquals("Unexpected exception detail message.", "Invalid number name specified. The string '" + to_translate + "' can not be translated into a number.", exc.getMessage());
			return;
		}
		fail("Expected an exception to be thrown for an invalid number name value, but none was thrown.");
	}
}
