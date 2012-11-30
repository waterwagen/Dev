package com.waterwagen.mris;

import java.util.HashMap;
import java.util.Map;

public class Utils
{
	public static final String 	EMAILADDRESS_FOR_JAKE = "peregrinet@gmail.com";
	public static final Id 		ID_FOR_JAKE = Id.valueOf("00000");
	public static final String 	SIMPLE_OUTPUT_FILE_PATH = "./mris_output_file.csv";
	
	public interface Converter<T>
	{
		public T convert(String str);
	}

	public static class NonConverter implements Converter<String>
	{
		@Override
		public String convert(String str)
		{
			return str;
		}		
	}
		
	public static class IdConverter implements Converter<Id>
	{
		@Override
		public Id convert(String str)
		{
			return Id.valueOf(str);
		}
	}

	public static Map<String,String> buildMap(String str)
	{
		Converter<String> value_converter = new NonConverter();
		Converter<String> key_converter = new NonConverter();
		return buildMap(str, key_converter, value_converter);
	}
	
	public static <K,V> Map<K,V> buildMap(String str, Converter<K> key_converter, Converter<V> value_converter)
	{
		Map<K,V> result = new HashMap<>();
		String[] map_entries = str.split(",");
		for(String entry : map_entries)
		{
			String[] key_and_value = entry.split(":");
			result.put(key_converter.convert(key_and_value[0]), value_converter.convert(key_and_value[1]));
		}
		return result;
	}
}
