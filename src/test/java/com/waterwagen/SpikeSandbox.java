package com.waterwagen;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class SpikeSandbox
{

	@Test
	public void test()
	{
		String str = "1111ab111ab111ab11";	
		Matcher matcher = Pattern.compile("ab").matcher(str);
		int counter = 0;
		while(matcher.find())
			counter++;
		assertEquals("Unexpected number of pattern matches.", 3, counter);
	}

}
