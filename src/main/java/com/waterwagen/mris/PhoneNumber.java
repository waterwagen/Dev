package com.waterwagen.mris;

import java.util.Map;
import java.util.WeakHashMap;

public class PhoneNumber
{
	private static Map<String,PhoneNumber> mCacheMap = new WeakHashMap<>();

	public static PhoneNumber valueOf(String phonenumber_value)
	{
		PhoneNumber result = mCacheMap.get(phonenumber_value);
		if(result == null)
		{
			result = new PhoneNumber(phonenumber_value);
			mCacheMap.put(phonenumber_value, result);
		}
		return result;
	}

	private String mPhoneNumber;

	private PhoneNumber(String phoneNumberValue)
	{
		mPhoneNumber = phoneNumberValue;
	}

	public String stringValue()
	{
		return mPhoneNumber;
	}
	
	@Override
	public String toString()
	{
		return String.format("PhoneNumber=%s", mPhoneNumber);
	}
}