package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.StandardAgentContactBuilder.*;
import static com.waterwagen.mris.TestingConstants.*;

import java.util.List;

import org.junit.Test;

public class TestSimpleContactsParser
{
	@Test
	public void test()
	{
		SimpleContactsParser parser = new SimpleContactsParser();
		StandardAgentContactBuilder jake_contact = aContact().
													withFirstName(FIRSTNAME_FOR_JAKE).
													withLastName(LASTNAME_FOR_JAKE).
													withId(ID_FOR_JAKE).
													and().withPhoneNumber(PHONENUMBER_FOR_JAKE);
		List<Contact> contacts_list = parser.parse(SIMPLE_INPUT_FILE_CONTENTS).list();
		assertThat(contacts_list.size(), is(equalTo(2)));
		assertThat(contacts_list.get(0), is(equalTo((Contact)jake_contact.build())));
		assertThat(contacts_list.get(1), is(equalTo((Contact)jake_contact.build())));
	}
}