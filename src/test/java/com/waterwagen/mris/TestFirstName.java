package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import org.junit.Before;
import org.junit.Test;

public class TestFirstName
{
	private FirstName mFirstName;
	private String mFirstNameValue;

	@Before
	public void setUp()
	{
		mFirstNameValue = "John";
		mFirstName = FirstName.valueOf(mFirstNameValue);		
	}
	
	@Test
	public void testStringValueIsTheFirstNameValuePassedOnCreation()
	{
		assertThat(mFirstName.stringValue(), is(equalTo(mFirstNameValue)));
	}

	@Test
	public void testMultipleValueOfInvocationsForTheSameFirstNameValueReturnTheSameInstance()
	{
		assertThat(FirstName.valueOf(mFirstNameValue), sameInstance(mFirstName));
	}
	
	@Test
	public void testToStringResult()
	{
		assertThat(mFirstName.toString(), is(equalTo("FirstName=" + mFirstNameValue)));
	}
}
