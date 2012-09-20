package com.waterwagen;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class TestScala
{
	@Test
	public void test()
	{
		Parser parser = new Parser();
		
		assertThat(parser.parse("string1string"), is(equalTo(1)));
		assertThat(parser.parse("string2string"), is(equalTo(2)));
		assertThat(parser.parse("stringstring3"), is(equalTo(3)));
		assertThat(parser.parse("string2string3"), is(equalTo(5)));
	}

}
