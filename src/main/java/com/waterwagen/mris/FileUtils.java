package com.waterwagen.mris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils
{
	public static void createFile(String file_name, String file_contents) throws IOException
	{
		Path file_path = Paths.get(file_name);
		Files.createFile(file_path);
		try(BufferedWriter writer = Files.newBufferedWriter(file_path, StandardCharsets.UTF_8, StandardOpenOption.WRITE))
		{
			writer.write(file_contents);
		}
	}
	
	public static String readFile(String file_path_str) throws IOException
	{
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(file_path_str), StandardCharsets.UTF_8))
		{
			String line;
			StringBuffer buffer = new StringBuffer();
			while((line = reader.readLine()) != null)
				buffer.append(line);
			return buffer.toString();	
		}
	}
	
	public static void deleteFile(String file_path) throws IOException
	{
		Files.delete(Paths.get(file_path));
	}
}
