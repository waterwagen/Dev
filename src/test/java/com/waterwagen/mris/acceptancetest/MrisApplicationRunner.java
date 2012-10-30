package com.waterwagen.mris.acceptancetest;

import java.lang.reflect.InvocationTargetException;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.ComponentLookupScope;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.util.Arrays;

import com.waterwagen.mris.Main;
import com.waterwagen.mris.MrisMainWindow;

public class MrisApplicationRunner
{
	static
	{
		FailOnThreadViolationRepaintManager.install();
	}

	private FrameFixture mFrameFixture;

	public void start()
	{
		try
		{
			Main.main(null);
			
			Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
			robot.settings().componentLookupScope(ComponentLookupScope.ALL); // do I want this (all) or only visible components?
			mFrameFixture = new FrameFixture(robot, Main.MAIN_WINDOW_COMPONENT_NAME);
		} 
		catch (InvocationTargetException | InterruptedException exc)
		{
			throw new RuntimeException("Failed to start the application.", exc);
		}
	}

	public void stop()
	{
		try{mFrameFixture.close();}catch (IllegalStateException exc){exc.printStackTrace();}
		mFrameFixture.cleanUp();
		mFrameFixture = null;
	}

	public void transformFile(String contacts_file_path)
	{
		mFrameFixture.textBox().setText(contacts_file_path);
		mFrameFixture.button().click();
	}
}
