package com.waterwagen.mris;

import static com.waterwagen.mris.Main.MAIN_WINDOW_COMPONENT_NAME;
import static com.waterwagen.mris.TestingConstants.SIMPLE_INPUT_FILE_CONTENTS;
import static com.waterwagen.mris.TestingConstants.SIMPLE_INPUT_FILE_PATH;

import java.io.IOException;

import net.java.openjdk.cacio.ctc.junit.CacioTestRunner;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.FrameFixture;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CacioTestRunner.class)
public class TestMrisMainWindowInteractions extends SwingTestCase
{
	private final Mockery context = new JUnit4Mockery();
	private MrisMainWindow mMrisMainWindow;
	
	@Before
	public void setUp() throws IOException
	{
		mMrisMainWindow = instanceOfMrisMainWindow();

		FileUtils.createFile(SIMPLE_INPUT_FILE_PATH, SIMPLE_INPUT_FILE_CONTENTS);
	}
	
	@After
	public void tearDown() throws IOException
	{
		FileUtils.deleteFile(SIMPLE_INPUT_FILE_PATH);		
	}

	@Test
	public void testButtonClickGeneratesTransformRequestNotification() throws IOException
	{
		final TransformRequestListener listener = context.mock(TransformRequestListener.class);
		context.checking(new Expectations()
		{{
			oneOf(listener).transformRequest(SIMPLE_INPUT_FILE_CONTENTS);
		}});				
		mMrisMainWindow.addTransformRequestListener(listener);
		
		display(mMrisMainWindow);			
		FrameFixture frame_fixture = frameFixture(MAIN_WINDOW_COMPONENT_NAME);
		frame_fixture.textBox().setText(SIMPLE_INPUT_FILE_PATH);
		frame_fixture.button().click();

		context.assertIsSatisfied();
	}

	//////////////////////
	/// Helper Methods ///
	//////////////////////

	private MrisMainWindow instanceOfMrisMainWindow()
	{
		return GuiActionRunner.execute(new GuiQuery<MrisMainWindow>()
		{
			@Override
			protected MrisMainWindow executeInEDT()
			{
				return new MrisMainWindow();
			}
		});
	}
	
	private void display(MrisMainWindow mrisMainWindow)
	{
		GuiActionRunner.execute(new GuiTask()
		{
			@Override
			protected void executeInEDT() throws Throwable
			{
				mMrisMainWindow.display();
			}
		});
	}
}