package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.StandardAgentContactBuilder.*;
import static com.waterwagen.mris.TestUtils.*;
import static com.waterwagen.mris.TestingConstants.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTestContactsTransformer
{
	private final Mockery context = new JUnit4Mockery();

	private ContactsTransformer mContactsTransformer;
	private ContactsTransformedListener listener;

	@Before
	public void setUp() throws Exception
	{
		mContactsTransformer = new ContactsTransformer(new SimpleContactsParser(), new LocalLookupEmailFinder(buildMap("00000:peregrinet@gmail.com")));
		listener = context.mock(ContactsTransformedListener.class);
		mContactsTransformer.addContactsTransformedListener(listener);
	}

	@Test
	public void test()
	{
		context.checking(new Expectations()
		{{
			oneOf(listener).contactsTransformed(SIMPLE_OUTPUT_FILE_CONTENTS);
		}});

		mContactsTransformer.transformRequest(SIMPLE_INPUT_FILE_CONTENTS);
	}
}
