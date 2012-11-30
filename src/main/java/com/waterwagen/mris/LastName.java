package com.waterwagen.mris;

import java.util.Map;
import java.util.WeakHashMap;

public class LastName
{
	private static Map<String,LastName> mCacheMap = new WeakHashMap<>();

	public static LastName valueOf(String firstname_value)
	{
		LastName result = mCacheMap.get(firstname_value);
		if(result == null)
		{
			result = new LastName(firstname_value);
			mCacheMap.put(firstname_value, result);
		}
		return result;
	}

	private String mLastName;

	private LastName(String firstNameValue)
	{
		mLastName = firstNameValue;
	}

	public String stringValue()
	{
		return mLastName;
	}
	
	@Override
	public String toString()
	{
		return String.format("LastName=%s", mLastName);
	}
}
