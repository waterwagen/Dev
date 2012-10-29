package com.waterwagen.mris;

public class FluentStringBuilder
{
	public static FluentStringBuilder buildStringStartingWith(String str)
	{
		return new FluentStringBuilder(str);
	}
	
	private StringBuilder mStringBuilder;

	private FluentStringBuilder(String str) 
	{
		mStringBuilder = new StringBuilder(str);
	}

	public FluentStringBuilder plus(String str)
	{
		mStringBuilder.append(str);
		return this;
	}

	public String end()
	{
		return mStringBuilder.toString();
	}
}
