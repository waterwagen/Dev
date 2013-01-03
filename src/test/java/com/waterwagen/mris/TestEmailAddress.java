package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestEmailAddress
{
	private String ADDRESS_STR = "john@gmail.com";
	
	@Test
	public void testStringValueIsTheEmailAddressPassedOnCreation()
	{
		assertThat(EmailAddress.valueOf(ADDRESS_STR).stringValue(), is(equalTo(ADDRESS_STR)));
	}

	@Test
	public void testThatANonNullStringIsRequiredToCreateAnInstance()
	{
		try
		{
			EmailAddress.valueOf(null);
			fail("Expected an exception to be thrown when a null String is passed to the EmailAddress constructor.");
		}
		catch(IllegalArgumentException exc)
		{
			assertThat(exc.getMessage(), is(equalTo("Can not construct a valid EmailAddress with a null String value.")));
		}
		catch(Exception exc)
		{
			fail("Unexpected exception type thrown when a null String is passed to the EmailAddress constructor. Type is " + exc.getClass().getSimpleName());
		}		
	}

	@Test
	public void testMultipleValueOfInvocationsForTheSamePhoneNumberValueReturnTheSameInstance()
	{
		assertThat(EmailAddress.valueOf(ADDRESS_STR), sameInstance(EmailAddress.valueOf(ADDRESS_STR)));
	}
	
	@Test
	public void testToStringResult()
	{
		assertThat(EmailAddress.valueOf(ADDRESS_STR).toString(), is(equalTo("EmailAddress=" + ADDRESS_STR)));
	}
}