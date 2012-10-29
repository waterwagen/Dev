package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.StandardAgentContactBuilder.*;
import static com.waterwagen.mris.TestUtils.*;
import static com.waterwagen.mris.TestingConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.fest.util.Maps;
import org.junit.Before;
import org.junit.Test;

public class TestLocalLookupEmailFinder
{
	private LocalLookupEmailFinder mFinder;
	private Contact mContact;
	private EmailAddress mExpectedEmailAddress;

	@Before
	public void setUp()
	{
		mFinder = new LocalLookupEmailFinder(buildMap(ID_FOR_JAKE + ":" + EMAILADDRESS_FOR_JAKE));

		mContact = aContact().withId(ID_FOR_JAKE).build();
		mExpectedEmailAddress = new EmailAddress(EMAILADDRESS_FOR_JAKE);		
	}
	
	@Test
	public void testFindsEmailAddressById()
	{
		assertThat(mFinder.findEmailAddress(mContact), is(equalTo(mExpectedEmailAddress)));
	}
}
