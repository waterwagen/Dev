package com.waterwagen.mris;

public interface Contact
{
	public void insertEmailAddress(EmailAddress address);

	public String getId();

	public String getLastName();

	public String getFirstName();

	public String getPhoneNumber();

	public EmailAddress getEmailAddress();
}
