package com.waterwagen.mris;

public class StandardAgentContactBuilder
{
	public static StandardAgentContactBuilder aContact()
	{
		return new StandardAgentContactBuilder();
	}

	private String mId = new Long(Long.MIN_VALUE).toString();
	private String mFirstName = "John";
	private String mLastName = "Doe";
	private String mPhoneNumber = "1112345678";
	
	public StandardAgentContactBuilder withId(String id)
	{
		mId = id;
		return this;
	}
	
	public StandardAgentContactBuilder withFirstName(String firstname)
	{
		mFirstName  = firstname;
		return this;
	}

	public StandardAgentContactBuilder withLastName(String lastname)
	{
		mLastName  = lastname;
		return this;
	}

	public StandardAgentContactBuilder withPhoneNumber(String phonenumber)
	{
		mPhoneNumber = phonenumber;
		return this;
	}

	public StandardAgentContactBuilder and()
	{
		// syntactic sugar
		return this;
	}

	public StandardAgentContact build()
	{
		return new StandardAgentContact(this.mId, this.mFirstName, this.mLastName, this.mPhoneNumber);
	}
}
