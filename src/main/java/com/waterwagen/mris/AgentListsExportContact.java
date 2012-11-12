package com.waterwagen.mris;

import static com.waterwagen.mris.FluentStringBuilder.buildStringStartingWith;

public class AgentListsExportContact implements Contact
{
	private String mPhoneNumber;
	private String mLastName;
	private String mFirstName;
	private String mId;
	private EmailAddress mEmailAddress;

	public AgentListsExportContact(String mId, String mFirstName, String mLastName, String mPhoneNumber)
	{
		this.mId = mId;
		this.mFirstName = mFirstName;
		this.mLastName = mLastName;
		this.mPhoneNumber = mPhoneNumber;
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
		result = 31 * result + this.mId.hashCode();
		result = 31 * result + this.mFirstName.hashCode();
		result = 31 * result + this.mLastName.hashCode();
		result = 31 * result + this.mPhoneNumber.hashCode();
		return result;
	}
	
	@Override
	public String toString()
	{
		return buildStringStartingWith(getClass().getSimpleName()).
				plus("[").
				plus("id=").plus(mId).
				plus(" firstName=").plus(mFirstName).
				plus(" lastName=").plus(mLastName).
				plus(" phoneNumber=").plus(mPhoneNumber).
				plus("]").end();
	}

	@Override
	public String getId()
	{
		return mId;
	}

	@Override
	public String getLastName()
	{
		return mLastName;
	}

	@Override
	public String getFirstName()
	{
		return mFirstName;
	}

	@Override
	public String getPhoneNumber()
	{
		return mPhoneNumber;
	}

	@Override
	public EmailAddress getEmailAddress()
	{
		return mEmailAddress;
	}
}
