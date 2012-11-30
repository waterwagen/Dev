package com.waterwagen.mris;

public class AgentListsExportContactBuilder
{
	public static AgentListsExportContactBuilder aContact()
	{
		return new AgentListsExportContactBuilder();
	}

	private Id mId = Id.valueOf(Long.valueOf(Long.MIN_VALUE).toString());
	private FirstName mFirstName = FirstName.valueOf("John");
	private LastName mLastName = LastName.valueOf("Doe");
	private PhoneNumber mPhoneNumber = PhoneNumber.valueOf("1112345678");

	public AgentListsExportContactBuilder withId(String id_str)
	{
		return withId(Id.valueOf(id_str));
	}
	
	public AgentListsExportContactBuilder withId(Id id)
	{
		mId = id;
		return this;
	}
	
	public AgentListsExportContactBuilder withFirstName(String firstname_str)
	{
		return withFirstName(FirstName.valueOf(firstname_str));
	}
	
	public AgentListsExportContactBuilder withFirstName(FirstName firstname)
	{
		mFirstName = firstname;
		return this;
	}

	public AgentListsExportContactBuilder withLastName(String lastname_str)
	{
		return withLastName(LastName.valueOf(lastname_str));
	}

	public AgentListsExportContactBuilder withLastName(LastName lastname)
	{
		mLastName = lastname;
		return this;
	}

	public AgentListsExportContactBuilder withPhoneNumber(String phonenumber_str)
	{
		return withPhoneNumber(PhoneNumber.valueOf(phonenumber_str));
	}

	public AgentListsExportContactBuilder withPhoneNumber(PhoneNumber phonenumber)
	{
		mPhoneNumber = phonenumber;
		return this;
	}

	public AgentListsExportContactBuilder and()
	{
		// syntactic sugar
		return this;
	}

	public AgentListsExportContact build()
	{
		return new AgentListsExportContact(mId, mFirstName, mLastName, mPhoneNumber);
	}
}
