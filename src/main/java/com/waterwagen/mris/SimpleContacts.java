package com.waterwagen.mris;

import java.util.Arrays;
import java.util.List;

public class SimpleContacts implements Contacts
{
	private List<Contact> mContactsList;

	public SimpleContacts(List<Contact> contacts)
	{
		this.mContactsList = contacts;
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

	private String surroundWithQuotes(String field)
	{
		return "\"" + field + "\"";
	}
}
