package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.AgentListsExportContactBuilder.*;
import static com.waterwagen.mris.TestUtils.*;

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
		assertThat(mBuilder.withId(id).build().getId(), is(equalTo(id)));
	}

	@Test
	public void testTheExpectedFirstNameIsReturnedFromTheGetter()
	{
		String firstname = "Josh";
		assertThat(mBuilder.withFirstName(firstname).build().getFirstName(), is(equalTo(firstname)));
	}

	@Test
	public void testTheExpectedLastNameIsReturnedFromTheGetter()
	{
		String lastname = "Taylor";
		assertThat(mBuilder.withLastName(lastname).build().getLastName(), is(equalTo(lastname)));
	}

	@Test
	public void testTheExpectedPhoneNumberIsReturnedFromTheGetter()
	{
		String phonenumber = "5402463579";
		assertThat(mBuilder.withPhoneNumber(phonenumber).build().getPhoneNumber(), is(equalTo(phonenumber)));
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
	
//	@Override
//	@Test
//	public void testDifferentInstancesWithSameValuesSatisfyEqualsMethod()
//	{
//		assertThat("Expected different StandardAgentContact instances with the same values to be equivalent through the equals() method.", 
//					mBuilder.build(), is(equalTo(mBuilder.build())));
//	}
//
//	@Override
//	@Test
//	public void testDistinctInstancesWithDifferentValuesDoNotSatisfyEqualsMethod()
//	{
//		assertThat("Expected different StandardAgentContact instances with different values to not be equivalent through the equals() method.", 
//					mBuilder.withFirstName("Billy").build(), is(not(equalTo(mBuilder.withFirstName("Bob").build()))));
//	}
//	
//	@Override
//	@Test
//	public void testHashCodeIsTheSameInInstancesWhichSatisfyTheEqualsMethod()
//	{
//		assertThat("Expected different StandardAgentContact instances with the same values to produce the same hashCode.", 
//				mBuilder.build().hashCode(), is(equalTo(mBuilder.build().hashCode())));
//	}
//	
//	@Override
//	@Test
//	public void testHashCodeIsDifferentInInstancesWhichDoNotSatisfyTheEqualsMethod()
//	{
//		assertThat("Expected different StandardAgentContact instances with different values to produce different hashCodes.", 
//				mBuilder.withFirstName("Billy").hashCode(), is(not(equalTo(mBuilder.withFirstName("Bob").build().hashCode()))));
//	}
//	
//	@Override
//	@Test
//	public void testThatTheSameHashCodeIsProducedForEachInvocationOfTheMethod()
//	{
//		StandardAgentContact contact = mBuilder.build(); 
//		int previous_hashcode = contact.hashCode();
//		int INVOCATION_COUNT = 100;
//		for(int i = 0; i < INVOCATION_COUNT; i++)
//		{
//			int hashcode = contact.hashCode();
//			assertThat("Expected the same hashCode to be returned for each invocation of the hashCode method.", 
//					 hashcode, is(equalTo(previous_hashcode)));
//			previous_hashcode = hashcode; 
//		}
//	}
//	
//	@Test
//	public void testThatTheToStringMethodReturnsARepresentativeValue()
//	{
//		String id = "123";
//		String firstname = "Joe";
//		String lastname = "Johnson";
//		String phonenumber = "123456789";
//		
//		String expected_tostring = buildStringStartingWith(StandardAgentContact.class.getSimpleName()).
//										plus("[").
//										plus("id=").plus(id).
//										plus(" firstName=").plus(firstname).
//										plus(" lastName=").plus(lastname).
//										plus(" phoneNumber=").plus(phonenumber).
//										plus("]").end();
//		StandardAgentContact contact = mBuilder.
//											withId(id).
//											withFirstName(firstname).
//											withLastName(lastname).
//											and().withPhoneNumber(phonenumber).build(); 
//		
//		assertThat("Unexpected result from the StandardAgentContact's toString() method.",
//					contact.toString(),
//					is(equalTo(expected_tostring)));
//	}
}
