package com.waterwagen.mris;

import java.lang.reflect.InvocationTargetException;

import org.fest.util.Arrays;
import org.junit.Test;

public class TestMain extends SwingTestCase
{
	@Test
	public void testGuiIsVisible() throws InvocationTargetException, InterruptedException
	{
		Main.main(null);
		assertFrameVisibility(Main.MAIN_WINDOW_COMPONENT_NAME, true);
	}
}
