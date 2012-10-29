package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class TestStandardAgentContactBuilder
{
	private StandardAgentContactBuilder mContactBuilder;

	@Before
	public void setUp()
	{
		mContactBuilder = new StandardAgentContactBuilder();
	}

	@Test
	public void testBuildProducesNewInstances()
	{
		assertThat(mContactBuilder.build(), is(not(sameInstance(mContactBuilder.build()))));
	}

}
