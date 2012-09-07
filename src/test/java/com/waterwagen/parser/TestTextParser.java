package com.waterwagen.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.waterwagen.parser.NumberParser;
import com.waterwagen.parser.NumberTranslator;
import com.waterwagen.parser.TextParser;

public class TestTextParser 
{
	private static final String TEST_VAL_PREFIX = "green";
	private static final String TEST_VAL_SUFFIX = "bush";

	@SuppressWarnings("serial")
	private static final Map<String,Integer> TEST_VALS = new HashMap<String,Integer>()
			{{put("one", 1);put("two", 2);put("three", 3);put("four", 4);put("five", 5);put("six", 6);put("seven", 7);put("eight", 8);put("nine", 9);}};

	private TextParser parser;
	
	@Before
	public void setUp() throws Exception 
	{
		parser = new TextParser(new MockNumberTranslator(), new MockNumberParser());
	}
	
	@Test
	public void testParseSingleDigitIntegersFromText()
	{
		for(Map.Entry<String,Integer> entry : TEST_VALS.entrySet())
		{
			List<Integer> result = parser.parseNumericalValues(TEST_VAL_PREFIX + entry.getKey() + TEST_VAL_SUFFIX);
			assertEquals("Unexpected value parsed.", entry.getValue(), result.get(0));
		}
	}
	
	@Test
	@Ignore
	public void testParseNamesFromText()
	{
		fail("This test not yet implemented.");
	}

	///////////////////////
	/// Utility Classes ///
	///////////////////////
	
	private static class MockNumberTranslator implements NumberTranslator
	{
		@Override
		public Integer translate(String string)
		{
			return TEST_VALS.get(string);
		}
	}

	private static class MockNumberParser implements NumberParser
	{
		@Override
		public List<String> parse(String string)
		{
			String result = string.replace(TEST_VAL_PREFIX, "");
			result = result.replace(TEST_VAL_SUFFIX, "");
			return Arrays.asList(result);
		}
	}
}