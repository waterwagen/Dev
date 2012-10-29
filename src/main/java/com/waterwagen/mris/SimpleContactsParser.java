package com.waterwagen.mris;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleContactsParser implements ContactsParser
{
	@Override
	public Contacts parse(String contacts_str)
	{
		return new SimpleContacts(parseContacts(contacts_str));
	}

	private List<Contact> parseContacts(String contacts_str)
	{
		List<Contact> result = new ArrayList<>();
		
		Matcher matcher = Pattern.compile("\"\\d{1,10}\" *, *\"\\w{1,20}\" *, *\"\\w{1,20}\" *, *\"\\d{10}\"").matcher(contacts_str.trim());
		while(matcher.find())
			result.add(buildContact(matcher.group()));
		System.out.println("list length = " + result.size());
		return result; 
	}

	private Contact buildContact(String contact_str)
	{
		System.out.println("called");
		List<String> fields = parseContactFields(contact_str);
		return new StandardAgentContact(fields.get(0), fields.get(2), fields.get(1), fields.get(3));
	}

	private List<String> parseContactFields(String contact_str)
	{
		List<String> result = new ArrayList<>();
		
		Matcher matcher = Pattern.compile("(?<=\")[\\w\\d']+(?=\")").matcher(contact_str.trim());
		while(matcher.find())
			result.add(matcher.group());
		
		return result;
	}
}