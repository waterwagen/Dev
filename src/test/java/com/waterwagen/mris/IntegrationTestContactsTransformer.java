package com.waterwagen.mris;

import static com.waterwagen.mris.TestUtils.*;
import static com.waterwagen.mris.TestingConstants.EMAILADDRESS_FOR_JAKE;
import static com.waterwagen.mris.TestingConstants.ID_FOR_JAKE;
import static com.waterwagen.mris.TestingConstants.SIMPLE_INPUT_FILE_CONTENTS;
import static com.waterwagen.mris.TestingConstants.SIMPLE_OUTPUT_FILE_CONTENTS;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class IntegrationTestContactsTransformer
{
	private final Mockery context = new JUnit4Mockery();

	private ContactsTransformer mContactsTransformer;
	private ContactsTransformedListener listener;

	@Before
	public void setUp() throws Exception
	{
		mContactsTransformer = new ContactsTransformer(new AgentListsExportContactsParser(), new LocalLookupEmailFinder(buildMap(ID_FOR_JAKE.stringValue() + ":" + EMAILADDRESS_FOR_JAKE, new IdConverter(), new NonConverter())));
		listener = context.mock(ContactsTransformedListener.class);
		mContactsTransformer.setContactsTransformedListener(listener);
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
