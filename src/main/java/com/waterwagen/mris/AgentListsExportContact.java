package com.waterwagen.mris;

import static com.waterwagen.mris.FluentStringBuilder.buildStringStartingWith;

public class AgentListsExportContact implements Contact
{
	private final PhoneNumber mPhoneNumber;
	private final LastName mLastName;
	private final FirstName mFirstName;
	private final Id mId;
	private EmailAddress mEmailAddress;

	public AgentListsExportContact(Id id, FirstName first_name, LastName last_name, PhoneNumber phone_number)
	{
		mId = id;
		mFirstName = first_name;
		mLastName = last_name;
		mPhoneNumber = phone_number;
	}

	@Override
	public void insertEmailAddress(EmailAddress address)
	{
		mEmailAddress = address;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other == this)
			return true;
		if(!(other instanceof AgentListsExportContact))
			return false;
		
		AgentListsExportContact other_contact = (AgentListsExportContact) other;
		return mId.equals(other_contact.mId) &&
				mFirstName.equals(other_contact.mFirstName) &&
				mLastName.equals(other_contact.mLastName) &&
				mPhoneNumber.equals(other_contact.mPhoneNumber);
	}	

	@Override
	public int hashCode()
	{
		int result = 17;
		result = 31 * result + mId.hashCode();
		result = 31 * result + mFirstName.hashCode();
		result = 31 * result + mLastName.hashCode();
		result = 31 * result + mPhoneNumber.hashCode();
		return result;
	}
	
	@Override
	public String toString()
	{
		return buildStringStartingWith(getClass().getSimpleName()).
				plus("[").
				plus("id=").plus(mId.stringValue()).
				plus(" firstName=").plus(mFirstName.stringValue()).
				plus(" lastName=").plus(mLastName.stringValue()).
				plus(" phoneNumber=").plus(mPhoneNumber.stringValue()).
				plus("]").end();
	}

	@Override
	public Id getId()
	{
		return mId;
	}

	@Override
	public LastName getLastName()
	{
		return mLastName;
	}

	@Override
	public FirstName getFirstName()
	{
		return mFirstName;
	}

	@Override
	public PhoneNumber getPhoneNumber()
	{
		return mPhoneNumber;
	}

	@Override
	public EmailAddress getEmailAddress()
	{
		return mEmailAddress;
	}
}
