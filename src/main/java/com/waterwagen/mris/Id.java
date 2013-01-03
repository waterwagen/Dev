package com.waterwagen.mris;

import java.util.Map;
import java.util.WeakHashMap;

public class Id
{
	private final static Map<String,Id> mCacheMap = new WeakHashMap<>();

	public static Id valueOf(String id_value)
	{
		if(id_value == null) 
			throw new IllegalArgumentException("Can not construct a valid Id with a null String value.");
		
		Id result = mCacheMap.get(id_value);
		if(result == null)
		{
			result = new Id(id_value);
			mCacheMap.put(id_value, result);
		}
		return result;
	}

	private final String mValue;

	private Id(String id_value)
	{
		mValue = id_value;
	}

	public String stringValue()
	{
		return mValue;
	}
	
	@Override
	public String toString()
	{
		return String.format("Id=%s", mValue);
	}
}