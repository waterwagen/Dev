package com.waterwagen.mris;

/**
 * A value class for email addresses.
 * 
 * @author waterwagen
 *
 */
public class EmailAddress
{
	private final String mAddressStr;

	public EmailAddress(String string)
	{
		if(string == null)
			throw new IllegalArgumentException("Can not construct a valid EmailAddress with a null String value.");
		
		mAddressStr = string;
	}

	public String stringValue()
	{
		return mAddressStr;
	}
	
	@Override
	public String toString()
	{
		return stringValue();
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other == this)
			return true;
		if(!(other instanceof EmailAddress))
			return false;
		
		EmailAddress other_contact = (EmailAddress) other;
		return mAddressStr.equals(other_contact.mAddressStr);
	}
	
	@Override
	public int hashCode()
	{
		int result = 17;
		result = 31 * result + mAddressStr.hashCode();
		return result;
	}
}