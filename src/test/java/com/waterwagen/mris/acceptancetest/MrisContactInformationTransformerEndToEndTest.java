package com.waterwagen.mris.acceptancetest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static com.waterwagen.mris.StandardAgentContactBuilder.*;
import static com.waterwagen.mris.TestUtils.*;
import static com.waterwagen.mris.TestingConstants.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.java.openjdk.cacio.ctc.junit.CacioTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.waterwagen.mris.TestingConstants;
import com.waterwagen.mris.FileUtils;

@RunWith(CacioTestRunner.class)
public class MrisContactInformationTransformerEndToEndTest
{
	private MrisApplicationRunner mApplication;
	
	@Before
	public void setUp() throws IOException
	{
		FileUtils.createFile(SIMPLE_INPUT_FILE_PATH, SIMPLE_INPUT_FILE_CONTENTS);
		
		// sanity check
		assertFileExists(SIMPLE_INPUT_FILE_PATH);
		assertFileContents(SIMPLE_INPUT_FILE_PATH, SIMPLE_INPUT_FILE_CONTENTS);

		mApplication = buildApplicationRunner();
		mApplication.start();
	}

	@After
	public void tearDown() throws IOException, InterruptedException
	{
		FileUtils.deleteFile(SIMPLE_INPUT_FILE_PATH);
		FileUtils.deleteFile(SIMPLE_OUTPUT_FILE_PATH); // cleanup, better way to do this?
		
		mApplication.stop();
	}

	/////////////
	/// Tests ///
	/////////////
	
	@Test
	public void testAddsEmailAddressesToContactInformation() throws IOException
	{
		mApplication.transformFile(SIMPLE_INPUT_FILE_PATH);
		
		// assert it did what it should
		assertFileExists(SIMPLE_OUTPUT_FILE_PATH);
		assertFileContents(SIMPLE_OUTPUT_FILE_PATH, SIMPLE_OUTPUT_FILE_CONTENTS);
	}

	////////////////////////////////
	/// Assertion Helper Methods ///
	////////////////////////////////
	
	private void assertFileContents(String file_path, String expected_file_contents) throws IOException
	{
		assertThat(FileUtils.readFile(file_path), is(equalTo(expected_file_contents)));
	}

	private void assertFileExists(String file_name)
	{
		boolean file_existence = Files.exists(Paths.get(file_name));
		assertThat(file_existence, is(true));
	}

	///////////////////////
	/// Utility Methods ///
	///////////////////////

	private MrisApplicationRunner buildApplicationRunner()
	{
		return new MrisApplicationRunnerVisible();
//		return new MrisApplicationRunnerHeadless();
	}
}