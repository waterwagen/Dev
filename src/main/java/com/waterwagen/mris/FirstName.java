package com.waterwagen.mris;

import java.util.Map;
import java.util.WeakHashMap;

public class FirstName
{
	private static Map<String,FirstName> mCacheMap = new WeakHashMap<>();

	public static FirstName valueOf(String firstname_value)
	{
		if(firstname_value == null) 
			throw new IllegalArgumentException("Can not construct a valid FirstName with a null String value.");

		FirstName result = mCacheMap.get(firstname_value);
		if(result == null)
		{
			result = new FirstName(firstname_value);
			mCacheMap.put(firstname_value, result);
		}
		return result;
	}

	private String mFirstName;

	private FirstName(String firstNameValue)
	{
		mFirstName = firstNameValue;
	}

	public String stringValue()
	{
		return mFirstName;
	}
	
	@Override
	public String toString()
	{
		return String.format("FirstName=%s", mFirstName);
	}
}
