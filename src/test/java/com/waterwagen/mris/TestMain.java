package com.waterwagen.mris;

import java.lang.reflect.InvocationTargetException;

import org.fest.util.Arrays;
import org.junit.Test;

public class TestMain extends SwingTestCase
{
	@Test
	public void testGuiIsVisibleByDefault() throws InvocationTargetException, InterruptedException
	{
		Main.main(null);
		assertFrameVisibility(Main.MAIN_WINDOW_COMPONENT_NAME, true);
	}
	
	@Test
	public void testGuiIsNotVisibleWhenHeadlessArgumentIsPassed() throws InvocationTargetException, InterruptedException
	{
		Main.main(Arrays.array("mris.headless=true"));
		assertFrameVisibility(Main.MAIN_WINDOW_COMPONENT_NAME, false);
	}
}
