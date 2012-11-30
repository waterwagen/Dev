package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class TestAgentListsExportContactBuilder
{
	private AgentListsExportContactBuilder mContactBuilder;

	@Before
	public void setUp()
	{
		mContactBuilder = new AgentListsExportContactBuilder();
	}

	@Test
	public void testBuildProducesNewInstances()
	{
		assertThat(mContactBuilder.build(), is(not(sameInstance(mContactBuilder.build()))));
	}

	@Test
	public void testAlternateMethodsProduceSameResult()
	{
		String id_str = "1";
		String firstname_str = "Joe";
		String lastname_str = "Blow";
		String phonenumber_str = "5401234567";
		AgentListsExportContactBuilder str_builder = new AgentListsExportContactBuilder().
																		withId(id_str).
																		withFirstName(firstname_str).
																		withLastName(lastname_str).
																		and().withPhoneNumber(phonenumber_str);

		Id id = Id.valueOf(id_str);
		FirstName firstname = FirstName.valueOf(firstname_str);
		LastName lastname = LastName.valueOf(lastname_str);
		PhoneNumber phonenumber = PhoneNumber.valueOf(phonenumber_str);
		AgentListsExportContactBuilder obj_builder = new AgentListsExportContactBuilder().
																		withId(id).
																		withFirstName(firstname).
																		withLastName(lastname).
																		and().withPhoneNumber(phonenumber);
		
		
		assertThat(str_builder.build(), is(equalTo(obj_builder.build())));
	}
}
