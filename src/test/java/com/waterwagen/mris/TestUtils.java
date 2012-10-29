package com.waterwagen.mris;

import java.util.HashMap;
import java.util.Map;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JTextComponentFixture;

public class TestUtils
{
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
	
	public static void clickButtonInFrame(FrameFixture frame_fixture)
	{
		final JButtonFixture button = frame_fixture.button();
		GuiActionRunner.execute(new GuiTask()
		{
			@Override
			protected void executeInEDT() throws Throwable
			{
				button.target.doClick();
			}
		});
	}

	public static void setTextInTextBoxInFrame(FrameFixture frame_fixture, final String textbox_text)
	{
		final JTextComponentFixture text_box = frame_fixture.textBox();
		GuiActionRunner.execute(new GuiTask()
		{
			@Override
			protected void executeInEDT() throws Throwable
			{
				text_box.target.setText(textbox_text);
			}
		});
	}
}
