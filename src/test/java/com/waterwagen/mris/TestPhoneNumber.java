package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestPhoneNumber
{
	private PhoneNumber mPhoneNumber;
	private String mPhoneNumberValue;

	@Before
	public void setUp()
	{
		mPhoneNumberValue = "5401234567";
		mPhoneNumber = PhoneNumber.valueOf(mPhoneNumberValue);		
	}

	@Test
	public void testThatANonNullStringIsRequiredToCreateAnInstance()
	{
		try
		{
			PhoneNumber.valueOf(null);
			fail("Expected an exception to be thrown when a null String is passed to the PhoneNumber constructor.");
		}
		catch(IllegalArgumentException exc)
		{
			assertThat(exc.getMessage(), is(equalTo("Can not construct a valid PhoneNumber with a null String value.")));
		}
		catch(Exception exc)
		{
			fail("Unexpected exception type thrown when a null String is passed to the PhoneNumber constructor. Type is " + exc.getClass().getSimpleName());
		}		
	}
	
	@Test
	public void testStringValueIsThePhoneNumberValuePassedOnCreation()
	{
		assertThat(mPhoneNumber.stringValue(), is(equalTo(mPhoneNumberValue)));
	}

	@Test
	public void testMultipleValueOfInvocationsForTheSamePhoneNumberValueReturnTheSameInstance()
	{
		assertThat(PhoneNumber.valueOf(mPhoneNumberValue), sameInstance(mPhoneNumber));
	}
	
	@Test
	public void testToStringResult()
	{
		assertThat(mPhoneNumber.toString(), is(equalTo("PhoneNumber=" + mPhoneNumberValue)));
	}
}
