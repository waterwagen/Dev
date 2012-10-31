package com.waterwagen.mris;

import com.google.inject.Inject;

public class ContactsTransformer implements TransformRequestListener
{
	private final ContactsParser mParser;
	private final EmailAddressFinder mFinder;
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
	public void setContactsTransformedListener(ContactsTransformedListener listener)
	{
		mListener = listener;
	}
}
