package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import com.waterwagen.test.helpers.EqualsHashcodeContractTester;

public class TestAgentListsExportContact extends EqualsHashcodeContractTester
{	
	private AgentListsExportContactBuilder mBuilder;

	@Before
	public void setUp()
	{
		mBuilder = new AgentListsExportContactBuilder();
	}

	@Test
	public void testTheExpectedIdIsReturnedFromTheGetter()
	{
		String id = "235235";
		assertThat(mBuilder.withId(id).build().getId(), is(equalTo(Id.valueOf(id))));
	}

	@Test
	public void testTheExpectedFirstNameIsReturnedFromTheGetter()
	{
		String firstname_str = "Josh";
		assertThat(mBuilder.withFirstName(firstname_str).build().getFirstName(), is(equalTo(FirstName.valueOf(firstname_str))));
	}

	@Test
	public void testTheExpectedLastNameIsReturnedFromTheGetter()
	{
		String lastname_str = "Taylor";
		assertThat(mBuilder.withLastName(lastname_str).build().getLastName(), is(equalTo(LastName.valueOf(lastname_str))));
	}

	@Test
	public void testTheExpectedPhoneNumberIsReturnedFromTheGetter()
	{
		String phonenumber_str = "5402463579";
		assertThat(mBuilder.withPhoneNumber(phonenumber_str).build().getPhoneNumber(), is(equalTo(PhoneNumber.valueOf(phonenumber_str))));
	}

	@Test
	public void testTheExpectedEmailAddressIsReturnedFromTheGetter()
	{
		EmailAddress emailaddress = new EmailAddress("bobby@isp.net");
		Contact contact = mBuilder.build();
		contact.insertEmailAddress(emailaddress);
		assertThat(contact.getEmailAddress(), is(equalTo(emailaddress)));
	}

	@Override
	public Object provideInstance()
	{
		return mBuilder.build();
	}

	@Override
	public InstancePair provideDifferentInstancesWithTheSameValues()
	{
		return new InstancePair(mBuilder.build(), mBuilder.build());
	}

	@Override
	public InstancePair provideDifferentInstancesWithDifferentValues()
	{
		return new InstancePair(mBuilder.withFirstName("Billy").build(), mBuilder.withFirstName("Bob").build());
	}
}
