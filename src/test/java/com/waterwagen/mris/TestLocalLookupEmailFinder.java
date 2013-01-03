package com.waterwagen.mris;

import static com.waterwagen.mris.AgentListsExportContactBuilder.aContact;
import static com.waterwagen.mris.TestUtils.buildMap;
import static com.waterwagen.mris.TestingConstants.EMAILADDRESS_FOR_JAKE;
import static com.waterwagen.mris.TestingConstants.ID_FOR_JAKE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.waterwagen.mris.TestUtils.IdConverter;
import com.waterwagen.mris.TestUtils.NonConverter;

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
		mExpectedEmailAddress = EmailAddress.valueOf(EMAILADDRESS_FOR_JAKE);		
	}
	
	@Test
	public void testFindsEmailAddressById()
	{
		assertThat(mFinder.findEmailAddressFor(mContact), is(equalTo(mExpectedEmailAddress)));
	}
}
