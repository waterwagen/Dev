package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.AgentListsExportContactBuilder.*;
import static com.waterwagen.mris.TestUtils.*;
import static com.waterwagen.mris.TestingConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.fest.util.Maps;
import org.junit.Before;
import org.junit.Test;

import com.waterwagen.mris.TestUtils.Converter;

public class TestLocalLookupEmailFinder
{
	private LocalLookupEmailFinder mFinder;
	private Contact mContact;
	private EmailAddress mExpectedEmailAddress;

	@Before
	public void setUp()
	{
		mFinder = new LocalLookupEmailFinder(buildMap(ID_FOR_JAKE.stringValue() + ":" + EMAILADDRESS_FOR_JAKE, new IdConverter(), new NonConverter()));

		mContact = aContact().withId(ID_FOR_JAKE).build();
		mExpectedEmailAddress = new EmailAddress(EMAILADDRESS_FOR_JAKE);		
	}
	
	@Test
	public void testFindsEmailAddressById()
	{
		assertThat(mFinder.findEmailAddressFor(mContact), is(equalTo(mExpectedEmailAddress)));
	}
}
