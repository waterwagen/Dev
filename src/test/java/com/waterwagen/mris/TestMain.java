package com.waterwagen.mris;

import java.lang.reflect.InvocationTargetException;

import net.java.openjdk.cacio.ctc.junit.CacioTestRunner;

import org.fest.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CacioTestRunner.class)
public class TestMain extends SwingTestCase
{
	@Test
	public void testGuiIsVisible() throws InvocationTargetException, InterruptedException
	{
		Main.main(null);
		assertFrameVisibility(Main.MAIN_WINDOW_COMPONENT_NAME, true);
	}
}
