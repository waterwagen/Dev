package com.waterwagen.mris;

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.StandardAgentContactBuilder.*;
import static com.waterwagen.mris.SimpleContactsBuilder.*;

import org.junit.Before;
import org.junit.Test;

public class TestSimpleContacts
{
	private AgentListsExportContactDocument mContacts;
	private Contact mContact2;
	private Contact mContact1;
	
	@Before
	public void setUp()
	{
		mContact1 = aContact().withFirstName("Billy").withLastName("Lee").withId("12345").and().withPhoneNumber("5401234567").build();
		mContact2 = aContact().withFirstName("Bob").withLastName("Johnson").withId("23456").withPhoneNumber("540987654").build();
		mContacts = aListOfContacts().withContact(mContact1).and().withContact(mContact2).build();
	}
	
	@Test
	public void testReturnsListOfInsertedContacts()
	{
		assertThat(mContacts.list(), hasItems(mContact1, mContact2));
	}
	
	@Test
	public void testThatStringValueAccuratelyRepresentsTheStateOfTheContacts()
	{
		String str = "\"12345\",\"Lee\",\"Billy\",\"5401234567\",\"23456\",\"Johnson\",\"Bob\",\"540987654\"";
		assertThat(mContacts.stringValue(), is(equalTo(str)));
	}
}
