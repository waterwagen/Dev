package com.waterwagen.mris;

public interface Contact
{
	public void insertEmailAddress(EmailAddress address);

	public Id getId();

	public LastName getLastName();

	public FirstName getFirstName();

	public PhoneNumber getPhoneNumber();

	public EmailAddress getEmailAddress();
}
