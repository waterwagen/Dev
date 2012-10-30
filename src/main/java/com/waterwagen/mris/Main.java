package com.waterwagen.mris;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main 
{
	public static final String MAIN_WINDOW_COMPONENT_NAME = "mrisMainWindow";

	public static void main(String[] argv) throws InvocationTargetException, InterruptedException
	{
		new Main().start();
	}
	
	private void start() throws InvocationTargetException, InterruptedException
	{
		startGui(Guice.createInjector(new MrisGuiceModule()));
	}
	
	private void startGui(final Injector injector) throws InvocationTargetException, InterruptedException
	{
		SwingUtilities.invokeAndWait(new Runnable()
		{
			@Override
			public void run()
			{
				MrisMainWindow main_window = injector.getInstance(MrisMainWindow.class);
				main_window.setName(MAIN_WINDOW_COMPONENT_NAME);
				main_window.display();
			}
		});
	}
}