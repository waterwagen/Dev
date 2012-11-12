package com.waterwagen.mris;

import java.util.ArrayList;
import java.util.List;

public class AgentListsExportContactsDocumentBuilder
{
	public static AgentListsExportContactsDocumentBuilder aListOfContacts()
	{
		return new AgentListsExportContactsDocumentBuilder();
	}

	private List<Contact> contacts = new ArrayList<>();
	
	public AgentListsExportContactsDocumentBuilder withContact(AgentListsExportContactBuilder contact)
	{
		return withContact(contact.build());
	}

	public AgentListsExportContactsDocumentBuilder withContact(Contact contact)
	{
		contacts.add(contact);
		return this;
	}

	public AgentListsExportContactsDocumentBuilder and()
	{
		// syntactic sugar
		return this;
	}

	public AgentListsExportContactsDocument build()
	{
		return new AgentListsExportContactsDocument(contacts);
	}
}
