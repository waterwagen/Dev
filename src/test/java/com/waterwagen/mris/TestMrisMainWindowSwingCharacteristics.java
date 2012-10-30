package com.waterwagen.mris;

import static com.waterwagen.mris.Main.MAIN_WINDOW_COMPONENT_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import net.java.openjdk.cacio.ctc.junit.CacioTestRunner;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CacioTestRunner.class)
public class TestMrisMainWindowSwingCharacteristics extends SwingTestCase
{
	private MrisMainWindow mMrisMainWindow;
	
	@Before
	public void setUp() throws IOException
	{
		mMrisMainWindow = instanceOfMrisMainWindow();
	}
	
	@Test
	public void testName()
	{
		assertThat(swingNameOf(mMrisMainWindow), is(equalTo(MAIN_WINDOW_COMPONENT_NAME)));
	}

	@Test
	public void testWindowNotVisibleBeforeDisplayCall()
	{
		assertFrameVisibility(MAIN_WINDOW_COMPONENT_NAME, false);
	}

	@Test
	public void testDisplayMakesWindowVisible() throws InterruptedException
	{
		display(mMrisMainWindow);
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

	private String swingNameOf(final MrisMainWindow mris_main_window)
	{
		String window_name = GuiActionRunner.execute(new GuiQuery<String>()
		{
			@Override
			protected String executeInEDT()
			{
				return mris_main_window.getName();
			}
		});
		return window_name;
	}
}