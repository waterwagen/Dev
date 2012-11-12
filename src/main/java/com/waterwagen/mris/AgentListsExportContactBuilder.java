package com.waterwagen.mris;

public class AgentListsExportContactBuilder
{
	public static AgentListsExportContactBuilder aContact()
	{
		return new AgentListsExportContactBuilder();
	}

	private String mId = new Long(Long.MIN_VALUE).toString();
	private String mFirstName = "John";
	private String mLastName = "Doe";
	private String mPhoneNumber = "1112345678";
	
	public AgentListsExportContactBuilder withId(String id)
	{
		mId = id;
		return this;
	}
	
	public AgentListsExportContactBuilder withFirstName(String firstname)
	{
		mFirstName  = firstname;
		return this;
	}

	public AgentListsExportContactBuilder withLastName(String lastname)
	{
		mLastName  = lastname;
		return this;
	}

	public AgentListsExportContactBuilder withPhoneNumber(String phonenumber)
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
		return new AgentListsExportContact(this.mId, this.mFirstName, this.mLastName, this.mPhoneNumber);
	}
}
