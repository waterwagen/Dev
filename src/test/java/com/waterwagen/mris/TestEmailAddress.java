package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.StandardAgentContactBuilder.*;
import static com.waterwagen.mris.TestUtils.*;

import org.junit.Before;
import org.junit.Test;

import com.waterwagen.test.helpers.EqualsHashcodeContractTester;

public class TestEmailAddress extends EqualsHashcodeContractTester
{
	private String ADDRESS_STR = "john@gmail.com";
	private String ADDRESS_STR_ALT = "billy@yahoo.net";	
	
	@Test
	public void testTheCorrectStringRepresentationOfTheEmailAddressIsReturnedFromTheGetter()
	{
		assertThat(new EmailAddress(ADDRESS_STR).stringValue(), is(equalTo(ADDRESS_STR)));
	}

	@Test
	public void testThatANonNullStringIsRequiredToCreateAnEmailAddressInstance()
	{
		try
		{
			new EmailAddress(null);
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
	
	@Override
	public Object provideInstance()
	{
		return new EmailAddress(ADDRESS_STR);
	}

	@Override
	public InstancePair provideDifferentInstancesWithTheSameValues()
	{
		return new InstancePair(new EmailAddress(ADDRESS_STR), new EmailAddress(ADDRESS_STR));
	}

	@Override
	public InstancePair provideDifferentInstancesWithDifferentValues()
	{
		return new InstancePair(new EmailAddress(ADDRESS_STR), new EmailAddress(ADDRESS_STR_ALT));
	}
}