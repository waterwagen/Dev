package com.waterwagen.mris.acceptancetest;

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.ComponentLookupScope;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.ContainerFixture;
import org.fest.swing.fixture.FrameFixture;
import org.fest.util.Arrays;

import com.waterwagen.mris.Main;
import com.waterwagen.mris.MrisMainWindow;

public abstract class AbstractMrisApplicationRunner implements MrisApplicationRunner
{
	static
	{
		FailOnThreadViolationRepaintManager.install();
	}

	private FrameFixture mFrameFixture;
	private boolean mIsRunning;

	@Override
	public void start()
	{
		markAsRunning();
		try
		{
			Main.main(Arrays.array("mris.headless=true"));
			
			Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
			robot.settings().componentLookupScope(ComponentLookupScope.ALL); // do I want this (all) or only visible components?
			mFrameFixture = new FrameFixture(robot, Main.MAIN_WINDOW_COMPONENT_NAME);
		} 
		catch (InvocationTargetException | InterruptedException exc)
		{
			markAsNotRunning();
			throw new RuntimeException("Failed to start the application.", exc);
		}
	}

	@Override
	public void stop()
	{
		markAsNotRunning();
		try{mFrameFixture.close();}catch (IllegalStateException exc){exc.printStackTrace();}
		mFrameFixture.cleanUp();
		mFrameFixture = null;
	}

	protected FrameFixture frameFixture()
	{
		if(!mIsRunning)
			throw new RuntimeException("The application is not running so the frame fixture is unavailable.");
		
		return mFrameFixture;
	}

	private void markAsNotRunning()
	{
		mIsRunning = false;
	}

	private void markAsRunning()
	{
		mIsRunning = true;
	}
}

//public AbstractMrisApplicationRunner()
//{
//	GuiActionRunner.execute(new GuiTask()
//	{
//		protected void executeInEDT() 
//		{
//			new MrisMainWindow();
//		}
//	});
//	Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
//	robot.settings().componentLookupScope(ComponentLookupScope.ALL); // do I want this or only visible components?
//	mFrameFixture = new FrameFixture(robot, Main.MAIN_WINDOW_COMPONENT_NAME);
//}