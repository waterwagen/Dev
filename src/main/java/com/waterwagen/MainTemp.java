package com.waterwagen;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MainTemp
{
	public static void main(String[] argv) throws IOException
	{
		Path test_dir = Paths.get("./src/main/java");		
		Files.walkFileTree(test_dir, new SimpleFileVisitor<Path>()
		{
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes arg1)
			{
				if(path.toString().endsWith(".java"))
				{
					System.out.println(path.normalize().subpath(3, path.getNameCount() - 1));
				}
				
				return FileVisitResult.CONTINUE;
			}
		});
	}
	
	public void printString() 
	{
		System.out.println("this is a test string println'd from " + this.getClass().getName());
	}
}
