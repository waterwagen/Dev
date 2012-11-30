package com.waterwagen.mris;

import java.util.List;

public class AgentListsExportContactsDocument implements ContactsDocument
{
	private List<Contact> mContactsList;

	public AgentListsExportContactsDocument(List<Contact> contacts)
	{
		mContactsList = contacts;
	}

	@Override
	public List<Contact> list()
	{
		return mContactsList;
	}

	@Override
	public String stringValue()
	{
		StringBuilder result = new StringBuilder();
		for(Contact contact : mContactsList)
		{
			result.append(surroundWithQuotes(contact.getId().stringValue())).
					append(",").
					append(surroundWithQuotes(contact.getLastName().stringValue())).
					append(",").
					append(surroundWithQuotes(contact.getFirstName().stringValue())).
					append(",").
					append(surroundWithQuotes(contact.getPhoneNumber().stringValue())).
					append(",").
					append(contact.getEmailAddress() != null ? (surroundWithQuotes(contact.getEmailAddress().stringValue())) : "").
					append(",");
			if(contact.getEmailAddress() == null)
				result.deleteCharAt(result.length() - 1);
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	private static String surroundWithQuotes(String field)
	{
		return "\"" + field + "\"";
	}
}
