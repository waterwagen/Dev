package com.waterwagen.mris;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class LocalLookupEmailFinder implements EmailAddressFinder
{
	private Map<String, String> mIdToEmailaddressMap;

	@Inject
	public LocalLookupEmailFinder(@Named("emailAddressMapping") Map<String, String> buildMap)
	{
		mIdToEmailaddressMap = buildMap;
	}

	@Override
	public EmailAddress findEmailAddress(Contact contact)
	{
		return new EmailAddress(mIdToEmailaddressMap.get(contact.getId()));
	}
}