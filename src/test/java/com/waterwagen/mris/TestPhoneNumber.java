package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

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
