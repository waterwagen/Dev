package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

public class TestLastName
{
	private LastName mLastName;
	private String mLastNameValue;

	@Before
	public void setUp()
	{
		mLastNameValue = "Doe";
		mLastName = LastName.valueOf(mLastNameValue);		
	}
	
	@Test
	public void testStringValueIsTheLastNameValuePassedOnCreation()
	{
		assertThat(mLastName.stringValue(), is(equalTo(mLastNameValue)));
	}

	@Test
	public void testMultipleValueOfInvocationsForTheSameLastNameValueReturnTheSameInstance()
	{
		assertThat(LastName.valueOf(mLastNameValue), sameInstance(mLastName));
	}
	
	@Test
	public void testToStringResult()
	{
		assertThat(mLastName.toString(), is(equalTo("LastName=" + mLastNameValue)));
	}
}
