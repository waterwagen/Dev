package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

public class TestId
{
	private Id mId;
	private String mIdValue;

	@Before
	public void setUp()
	{
		mIdValue = "123";
		mId = Id.valueOf(mIdValue);		
	}
	
	@Test
	public void testStringValueIsTheIdValuePassedOnCreation()
	{
		assertThat(mId.stringValue(), is(equalTo(mIdValue)));
	}

	@Test
	public void testMultipleValueOfInvocationsForTheSameIdValueReturnTheSameInstance()
	{
		assertThat(Id.valueOf(mIdValue), sameInstance(mId));
	}
	
	@Test
	public void testToStringResult()
	{
		assertThat(mId.toString(), is(equalTo("Id=" + mIdValue)));
	}
}
