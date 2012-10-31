package com.waterwagen.mris;

import com.google.inject.Inject;

public class ContactsTransformer implements TransformRequestListener
{
	private ContactsParser mParser;
	private EmailAddressFinder mFinder;
	private ContactsTransformedListener mListener;

	@Inject
	public ContactsTransformer(ContactsParser parser, EmailAddressFinder finder)
	{
		mParser = parser;
		mFinder = finder;
	}
	
	@Override
	public void transformRequest(String contacts_str)
	{
		ContactsDocument contacts = mParser.parse(contacts_str);
		for(Contact contact : contacts.list())
			contact.insertEmailAddress(mFinder.findEmailAddressFor(contact));
		mListener.contactsTransformed(contacts.stringValue());
	}

	@Inject
	public void addContactsTransformedListener(ContactsTransformedListener listener)
	{
		mListener = listener;
	}
}
