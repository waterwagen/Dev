package com.waterwagen.mris;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.StandardAgentContactBuilder.*;
import static com.waterwagen.mris.TestUtils.*;
import static com.waterwagen.mris.TestingConstants.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSimpleFileWriter
{
	private SimpleFileWriter mWriter;
	
	@Before
	public void setUp()
	{
		mWriter = new SimpleFileWriter(SIMPLE_OUTPUT_FILE_PATH);
	}

	@After
	public void tearDown() throws IOException
	{
		FileUtils.deleteFile(SIMPLE_OUTPUT_FILE_PATH);
	}
	
	@Test
	public void testWritesContactsStringAsReceived() throws IOException
	{
		mWriter.contactsTransformed(SIMPLE_OUTPUT_FILE_CONTENTS);
		assertThat(Files.exists(Paths.get(SIMPLE_OUTPUT_FILE_PATH)), is(equalTo(true)));
		assertThat(FileUtils.readFile(SIMPLE_OUTPUT_FILE_PATH), is(equalTo(SIMPLE_OUTPUT_FILE_CONTENTS)));
	}
}
