package com.waterwagen.mris;

import static com.waterwagen.mris.Main.MAIN_WINDOW_COMPONENT_NAME;
import static com.waterwagen.mris.TestUtils.clickButtonInFrame;
import static com.waterwagen.mris.TestUtils.setTextInTextBoxInFrame;
import static com.waterwagen.mris.TestingConstants.SIMPLE_INPUT_FILE_CONTENTS;
import static com.waterwagen.mris.TestingConstants.SIMPLE_INPUT_FILE_PATH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import net.java.openjdk.cacio.ctc.junit.CacioTestRunner;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class TestMrisMainWindow extends SwingTestCase
{
	private final Mockery context = new JUnit4Mockery();
	private MrisMainWindow mMrisMainWindow;
	
	@Before
	public void setUp() throws IOException
	{
		mMrisMainWindow = GuiActionRunner.execute(new GuiQuery<MrisMainWindow>()
		{
			@Override
			protected MrisMainWindow executeInEDT()
			{
				return new MrisMainWindow();
			}
		});
	}
	
	@Test
	public void testName()
	{
		String window_name = GuiActionRunner.execute(new GuiQuery<String>()
		{
			@Override
			protected String executeInEDT()
			{
				return mMrisMainWindow.getName();
			}
		});
		assertThat(window_name, is(MAIN_WINDOW_COMPONENT_NAME));
	}

	@Test
	public void testWindowNotVisibleBeforeDisplayCall()
	{
		assertFrameVisibility(MAIN_WINDOW_COMPONENT_NAME, false);
	}

	@Test
	public void testDisplayMakesWindowVisible() throws InterruptedException
	{
		GuiActionRunner.execute(new GuiTask()
		{
			@Override
			protected void executeInEDT() throws Throwable
			{
				mMrisMainWindow.display();
			}
		});
		assertFrameVisibility(MAIN_WINDOW_COMPONENT_NAME, true);
	}
	
	@Test
	public void testContainsButton()
	{
		assertThat(frameFixture(MAIN_WINDOW_COMPONENT_NAME).button(), is(notNullValue()));
	}
	
	@Test
	public void testButtonName()
	{
		assertThat(frameFixture(MAIN_WINDOW_COMPONENT_NAME).button().target.getName(), is(equalTo("submitTransformRequestButton")));
	}
	
	@Test
	public void testContainsTextComponent()
	{
		assertThat(frameFixture(MAIN_WINDOW_COMPONENT_NAME).textBox(), is(notNullValue()));
	}
	
	@Test
	public void testTextComponentName()
	{
		assertThat(frameFixture(MAIN_WINDOW_COMPONENT_NAME).textBox().target.getName(), is(equalTo("inputFilePathTextBox")));
	}
	
	@Test
	public void testButtonClickGeneratesTransformRequestNotification() throws IOException
	{
		try
		{
			FileUtils.createFile(SIMPLE_INPUT_FILE_PATH, SIMPLE_INPUT_FILE_CONTENTS);
			final TransformRequestListener listener = context.mock(TransformRequestListener.class);
			mMrisMainWindow.addTransformRequestListener(listener);
			GuiActionRunner.execute(new GuiTask()
			{
				@Override
				protected void executeInEDT() throws Throwable
				{
					mMrisMainWindow.display();
				}
			});			
			context.checking(new Expectations()
			{{
				oneOf(listener).transformRequest(SIMPLE_INPUT_FILE_CONTENTS);
			}});
					
			FrameFixture frame_fixture = frameFixture(MAIN_WINDOW_COMPONENT_NAME);
			setTextInTextBoxInFrame(frame_fixture, SIMPLE_INPUT_FILE_PATH);		
			clickButtonInFrame(frame_fixture);
//			final JButtonFixture button = frame_fixture.button();
//			button.click();
	//		GuiActionRunner.execute(new GuiTask()
	//		{
	//			@Override
	//			protected void executeInEDT() throws Throwable
	//			{
	//				button.target.doClick();
	//			}
	//		});
			context.assertIsSatisfied();
		}
		finally
		{
			// clean up
			FileUtils.deleteFile(SIMPLE_INPUT_FILE_PATH);
		}
	}
}