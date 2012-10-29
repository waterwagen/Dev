package com.waterwagen.mris;

import java.io.IOException;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SimpleFileWriter implements ContactsTransformedListener
{
	private String mFilePath;

	@Inject
	public SimpleFileWriter(@Named("mrisOutputFilePath") String simpleOutputFilePath)
	{
		mFilePath = simpleOutputFilePath;
	}

	private void write(String simpleOutputFilePath, String simpleOutputFileContents) throws IOException
	{
		FileUtils.createFile(simpleOutputFilePath, simpleOutputFileContents);
	}

	@Override
	public void contactsTransformed(String expected_contacts_with_emailaddresses)
	{
		try
		{
			this.write(mFilePath, expected_contacts_with_emailaddresses);
		} 
		catch (IOException exc)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException(exc);
		}
	}

}
