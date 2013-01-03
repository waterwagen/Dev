package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.fail;

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
	public void testThatANonNullStringIsRequiredToCreateAnInstance()
	{
		try
		{
			Id.valueOf(null);
			fail("Expected an exception to be thrown when a null String is passed to the Id constructor.");
		}
		catch(IllegalArgumentException exc)
		{
			assertThat(exc.getMessage(), is(equalTo("Can not construct a valid Id with a null String value.")));
		}
		catch(Exception exc)
		{
			fail("Unexpected exception type thrown when a null String is passed to the Id constructor. Type is " + exc.getClass().getSimpleName());
		}		
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
