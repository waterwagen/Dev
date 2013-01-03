package com.waterwagen.mris;

import static com.waterwagen.mris.AgentListsExportContactBuilder.aContact;

import java.io.IOException;
import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.States;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class TestContactsTransformer
{
	private final String INPUT_CONTACTS = TestingConstants.SIMPLE_INPUT_FILE_CONTENTS;
	private final String EXPECTED_OUTPUT_CONTACTS = TestingConstants.SIMPLE_OUTPUT_FILE_CONTENTS;

	private final Mockery context = new JUnit4Mockery();
	private ContactsTransformer mContactsTransformer;
	/* mock objects */
	// dependencies
	private ContactsParser mParserMock;
	private EmailAddressFinder mFinderMock;
	// notifications
	private ContactsTransformedListener listener;
	// values
//	private Contact mJakeContactMock1;
//	private Contact mJakeContactMock2;
	private Contact mJakeContact1;
	private Contact mJakeContact2;
	private ContactsDocument mContactsMock;
	private EmailAddress mJakeEmailAddress;
//	private ContactsDocument mContacts;

	@Before
	public void setUp()
	{
		mParserMock = context.mock(ContactsParser.class);
		mFinderMock = context.mock(EmailAddressFinder.class);
		listener = context.mock(ContactsTransformedListener.class);
		mContactsMock = context.mock(ContactsDocument.class);
//		mJakeContactMock1 = context.mock(Contact.class, "contact1");
//		mJakeContactMock2 = context.mock(Contact.class, "contact2");

		AgentListsExportContactBuilder builder = aContact().withFirstName("Jacob").withLastName("Taylor").withId(Id.valueOf("00000")).and().withPhoneNumber(PhoneNumber.valueOf("5401234567")); 
		mJakeContact1 = builder.build();
		mJakeContact2 = builder.build();
//		mContacts = aListOfContacts().withContact(mJakeContact1).and().withContact(mJakeContact2).build();
		mJakeEmailAddress = EmailAddress.valueOf(TestingConstants.EMAILADDRESS_FOR_JAKE);

		mContactsTransformer = new ContactsTransformer(mParserMock, mFinderMock);
		mContactsTransformer.setContactsTransformedListener(listener);
	}

	@Test
	public void testNotifiesContactsTransformedListenerWithContactsNowIncludingEmailAddresses()
	{
		context.checking(new Expectations()
		{{
			ignoring(mFinderMock); 
			ignoring(mContactsMock).list();

			allowing(mParserMock).parse(INPUT_CONTACTS); will(returnValue(mContactsMock)); 
			allowing(mContactsMock).stringValue(); will(returnValue(EXPECTED_OUTPUT_CONTACTS));

			// expectations
			oneOf(listener).contactsTransformed(EXPECTED_OUTPUT_CONTACTS);
		}});

		mContactsTransformer.transformRequest(TestingConstants.SIMPLE_INPUT_FILE_CONTENTS);
	}
	
	@Test
	public void testNotifiesContactsTranformedListenerAfterTransformationIsComplete() throws IOException
	{		
		context.checking(new Expectations()
		{{
			final States state = context.states("transformation").startsAs("started");
			
			allowing(mContactsMock).list(); 
				will(returnValue(Arrays.asList(mJakeContact1, mJakeContact2)));
			allowing(mParserMock).parse(INPUT_CONTACTS); 
				will(returnValue(mContactsMock)); 
				then(state.is("parsed"));
			allowing(mFinderMock).findEmailAddressFor(with(equal(mJakeContact1))); 
				will(returnValue(mJakeEmailAddress)); 
				then(state.is("inserted"));																						
			allowing(mFinderMock).findEmailAddressFor(with(equal(mJakeContact2))); 
				will(returnValue(mJakeEmailAddress)); 
				then(state.is("inserted"));																						
			allowing(mContactsMock).stringValue(); 
				when(state.is("inserted"));
				will(returnValue(EXPECTED_OUTPUT_CONTACTS));  
				then(state.is("transformed"));

			// expectations
			oneOf(listener).contactsTransformed(with(any(String.class))); when(state.is("transformed"));
		}});

		mContactsTransformer.transformRequest(TestingConstants.SIMPLE_INPUT_FILE_CONTENTS);
	}

	@Test
	public void testUsesEmailFinderForEachContact()
	{
		context.checking(new Expectations()
		{{
			ignoring(listener);
			ignoring(mContactsMock).stringValue();

			allowing(mParserMock).parse(INPUT_CONTACTS); will(returnValue(mContactsMock));
			allowing(mContactsMock).list(); will(returnValue(Arrays.asList(mJakeContact1, mJakeContact2)));
	
			// expectations
			oneOf(mFinderMock).findEmailAddressFor(with(equal(mJakeContact1)));																						
			oneOf(mFinderMock).findEmailAddressFor(with(equal(mJakeContact2)));																						
		}});

		mContactsTransformer.transformRequest(TestingConstants.SIMPLE_INPUT_FILE_CONTENTS);		
	}

	@Test
	public void testUsesParserForInputContacts()
	{
		context.checking(new Expectations()
		{{
			ignoring(mContactsMock);
			ignoring(mFinderMock);																																											
			ignoring(listener);

			// expectations
			oneOf(mParserMock).parse(INPUT_CONTACTS);
		}});

		mContactsTransformer.transformRequest(TestingConstants.SIMPLE_INPUT_FILE_CONTENTS);		
	}	
//	
//	StandardAgentContactBuilder jake = aContact().
//		withId("00000").
//		withFirstName("Jacob").
//		withLastName("Taylor").
//		and().withPhoneNumber("5401234567");
//	SimpleContactsBuilder contacts_list = aListOfContacts().
//		withContact(jake).
//		and().withContact(jake);
}
