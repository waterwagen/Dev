package com.waterwagen.mris;

import java.util.ArrayList;
import java.util.List;

public class SimpleContactsBuilder
{
	public static SimpleContactsBuilder aListOfContacts()
	{
		return new SimpleContactsBuilder();
	}

	private List<Contact> contacts = new ArrayList<>();
	
	public SimpleContactsBuilder withContact(StandardAgentContactBuilder contact)
	{
		return withContact(contact.build());
	}

	public SimpleContactsBuilder withContact(Contact contact)
	{
		contacts.add(contact);
		return this;
	}

	public SimpleContactsBuilder and()
	{
		// syntactic sugar
		return this;
	}

	public AgentListsExportContactDocument build()
	{
		return new AgentListsExportContactDocument(contacts);
	}
}
