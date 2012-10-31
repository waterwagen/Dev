package com.waterwagen.mris;

import java.util.List;

public class AgentListsExportContactDocument implements ContactsDocument
{
	private List<Contact> mContactsList;

	public AgentListsExportContactDocument(List<Contact> contacts)
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
			result.append(surroundWithQuotes(contact.getId())).
					append(",").
					append(surroundWithQuotes(contact.getLastName())).
					append(",").
					append(surroundWithQuotes(contact.getFirstName())).
					append(",").
					append(surroundWithQuotes(contact.getPhoneNumber())).
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
