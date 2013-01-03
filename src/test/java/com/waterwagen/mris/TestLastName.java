package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.fail;

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
	public void testThatANonNullStringIsRequiredToCreateAnInstance()
	{
		try
		{
			LastName.valueOf(null);
			fail("Expected an exception to be thrown when a null String is passed to the LastName constructor.");
		}
		catch(IllegalArgumentException exc)
		{
			assertThat(exc.getMessage(), is(equalTo("Can not construct a valid LastName with a null String value.")));
		}
		catch(Exception exc)
		{
			fail("Unexpected exception type thrown when a null String is passed to the LastName constructor. Type is " + exc.getClass().getSimpleName());
		}		
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
