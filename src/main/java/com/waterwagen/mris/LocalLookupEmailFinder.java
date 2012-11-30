package com.waterwagen.mris;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class LocalLookupEmailFinder implements EmailAddressFinder
{
	private Map<Id, String> mIdToEmailaddressMap;

	@Inject
	public LocalLookupEmailFinder(@Named("emailAddressMapping") Map<Id, String> buildMap)
	{
		mIdToEmailaddressMap = buildMap;
	}

	@Override
	public EmailAddress findEmailAddressFor(Contact contact)
	{
		return new EmailAddress(mIdToEmailaddressMap.get(contact.getId()));
	}
}
