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
	public interface Converter<T>
	{
		public T convert(String str);
	}

	public static class NonConverter implements Converter<String>
	{
		@Override
		public String convert(String str)
		{
			return str;
		}		
	}
		
	public static class IdConverter implements Converter<Id>
	{
		@Override
		public Id convert(String str)
		{
			return Id.valueOf(str);
		}
	}

	public static Map<String,String> buildMap(String str)
	{
		Converter<String> value_converter = new NonConverter();
		Converter<String> key_converter = new NonConverter();
		return buildMap(str, key_converter, value_converter);
	}
	
	public static <K,V> Map<K,V> buildMap(String str, Converter<K> key_converter, Converter<V> value_converter)
	{
		Map<K,V> result = new HashMap<>();
		String[] map_entries = str.split(",");
		for(String entry : map_entries)
		{
			String[] key_and_value = entry.split(":");
			result.put(key_converter.convert(key_and_value[0]), value_converter.convert(key_and_value[1]));
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
