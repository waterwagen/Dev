package com.waterwagen.mris;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses the contacts from the MRIS system from a certain file export definition in the system.
 * A risk is that this export gets deleted or changed, resulting in this parser failing. If a new
 * export format is used, a new parser will need to be built and configured in the system to handle it.
 * This parser handles files from the export definition called 'AgentLists' which has the following fields:
 * <ul>
 * 	<li>ListAgentID</li>
 * 	<li>ListAgentLastName</li>
 * 	<li>ListAgentFirstName</li>
 * 	<li>ListAgentCellPhone</li>
 * 	<li>SellAgentID</li>
 * 	<li>SellAgentLastName</li>
 * 	<li>SellAgentFirstName</li>
 * 	<li>SellAgentCellPhone</li>
 * <ul></ul>
 * 
 * @author waterwagen
 *
 */
public class AgentListsExportContactsParser implements ContactsParser
{
	private static final String CONTACT_PATTERN_STR = 	"\"\\d{1,10}\" *, # matches the MRIS agent ID\n" +
														" *\"\\w{1,20}\" *, # matches the agent's last name\n" +
														" *\"\\w{1,20}\" *, # matches the agent's first name\n" +
														" *\"\\d{10}\" # matches the agent's cell phone number\n";
	private static final String FIELD_PATTERN_STR = "(?<=\") # checks for the leading quote symbol but excludes it from the matched group \n" +
													"[\\w']+ # match the actual field data between the quotes. Should only have word characters \n" +
													"(?=\") # checks for the trailing quote symbol but excludes it from the matched group \n";
	
	private final Pattern mContactPattern = Pattern.compile(CONTACT_PATTERN_STR, Pattern.COMMENTS);
	private final Pattern mFieldPattern = Pattern.compile(FIELD_PATTERN_STR, Pattern.COMMENTS);

	@Override
	public ContactsDocument parse(String contacts_str)
	{
		return new AgentListsExportContactDocument(parseContacts(contacts_str));
	}

	private List<Contact> parseContacts(String contacts_str)
	{
		List<Contact> result = new ArrayList<>();

		List<String> matched_contacts = findAllMatches(mContactPattern, contacts_str);
		for(String contact : matched_contacts)
			result.add(buildContact(contact));
		
		return result; 
	}

	private Contact buildContact(String contact_str)
	{
		List<String> fields = parseContactFields(contact_str);
		return new StandardAgentContactBuilder().
						withId(fields.get(0)).
						withFirstName(fields.get(2)).
						withLastName(fields.get(1)).
						and().withPhoneNumber(fields.get(3)).build();
	}

	private List<String> parseContactFields(String contact_str)
	{
		return findAllMatches(mFieldPattern, contact_str);
	}
		
	private List<String> findAllMatches(Pattern pattern, String str)
	{
		List<String> result = new ArrayList<>();
		
		Matcher matcher = pattern.matcher(str.trim());
		while(matcher.find())
			result.add(matcher.group());
		
		return result;
	}
}