package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.fail;

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
	public void testThatANonNullStringIsRequiredToCreateAnInstance()
	{
		try
		{
			FirstName.valueOf(null);
			fail("Expected an exception to be thrown when a null String is passed to the FirstName constructor.");
		}
		catch(IllegalArgumentException exc)
		{
			assertThat(exc.getMessage(), is(equalTo("Can not construct a valid FirstName with a null String value.")));
		}
		catch(Exception exc)
		{
			fail("Unexpected exception type thrown when a null String is passed to the FirstName constructor. Type is " + exc.getClass().getSimpleName());
		}		
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
