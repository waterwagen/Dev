package com.waterwagen.mris;

import java.util.HashMap;
import java.util.Map;

public class Utils
{
	public static final String EMAILADDRESS_FOR_JAKE = "peregrinet@gmail.com";
	public static final String ID_FOR_JAKE = "00000";
	public static final String SIMPLE_OUTPUT_FILE_PATH = "./mris_output_file.csv";

	public static Map<String,String> buildMap(String str)
	{
		Map<String,String> result = new HashMap<>();
		String[] map_entries = str.split(",");
		for(String entry : map_entries)
		{
			String[] key_value = entry.split(":");
			result.put(key_value[0], key_value[1]);
		}
		return result;
	}
}
