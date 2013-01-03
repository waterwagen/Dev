package com.waterwagen.mris;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * A value type for email addresses.
 * 
 * @author waterwagen
 *
 */
public class EmailAddress
{
	private static Map<String,EmailAddress> mCacheMap = new WeakHashMap<>();

	public static EmailAddress valueOf(String emailaddress_value)
	{
		if(emailaddress_value == null) 
			throw new IllegalArgumentException("Can not construct a valid EmailAddress with a null String value.");
		
		EmailAddress result = mCacheMap.get(emailaddress_value);
		if(result == null)
		{
			result = new EmailAddress(emailaddress_value);
			mCacheMap.put(emailaddress_value, result);
		}
		return result;
	}

	private final String mAddress;

	private EmailAddress(String string)
	{
		mAddress = string;
	}

	public String stringValue()
	{
		return mAddress;
	}
	
	@Override
	public String toString()
	{
		return String.format("EmailAddress=%s", mAddress);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other == this)
			return true;
		if(!(other instanceof EmailAddress))
			return false;
		
		EmailAddress other_contact = (EmailAddress) other;
		return mAddress.equals(other_contact.mAddress);
	}
	
	@Override
	public int hashCode()
	{
		int result = 17;
		result = 31 * result + mAddress.hashCode();
		return result;
	}
}