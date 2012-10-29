package com.waterwagen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.ComponentLookupScope;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JLabelFixture;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SpikeSandbox
{
	private FrameFixture mFrameFixture;

	@BeforeClass 
	public static void setUpClass() 
	{
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUpTest() throws InvocationTargetException, InterruptedException
	{
		System.out.println("@Before 1 executed");
//		TestJFrame test_frame = GuiActionRunner.execute(new GuiQuery<TestJFrame>() 
//		{
//			protected TestJFrame executeInEDT() 
//			{
//				return new TestJFrame();
//			}
//		});
		Thread app_thread = new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Main.main(null);
				} 
				catch (InvocationTargetException | InterruptedException exc)
				{
					exc.printStackTrace();
				}
			}
		};
		app_thread.start();
		Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
		robot.settings().componentLookupScope(ComponentLookupScope.ALL);
		mFrameFixture = new FrameFixture(robot, "testWindow");
//		mFrameFixture.target.setVisible(true);
	}
	
	@Before
	public void setUp2() throws InvocationTargetException, InterruptedException
	{
		System.out.println("@Before 2 executed");
	}
	
	@Before
	public void setUp3() throws InvocationTargetException, InterruptedException
	{
		System.out.println("@Before 3 executed");
	}
	
	@Test
	public void test()
	{
		JButtonFixture button = mFrameFixture.button();
		assertThat(button, notNullValue());

		JLabelFixture label = mFrameFixture.label("label1");
		assertThat(label, notNullValue());
		assertThat(label.text(), is(equalTo("Label One")));

		label = mFrameFixture.label("label2");
		assertThat(label, notNullValue());
		assertThat(label.text(), is(equalTo("Label Two")));
	}
	
	private static class Main 
	{
		public static void main(String[] argv) throws InvocationTargetException, InterruptedException
		{
			new Main().start();
		}
		
		private TestWindow mMainGui = null;
		
		public void start() throws InvocationTargetException, InterruptedException
		{
			startGui();
		}
		
		private void startGui() throws InvocationTargetException, InterruptedException
		{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					mMainGui = new TestWindow();
					mMainGui.setName("testWindow");
					mMainGui.setVisible(true);
				}
			});
		}
	}
	
	private static class TestWindow extends JFrame
	{
		private JButton mClickMe = new JButton("Click Me!");
		private JLabel mLabel1 = new JLabel("Label One");
		private JLabel mLabel2 = new JLabel("Label Two");		
		
		private TestWindow()
		{
			this.add(mClickMe);
			mLabel1.setName("label1");
			mLabel1.addMouseListener(buildPrintingMouseListener("Label 1 was clicked on!"));
			this.add(mLabel1);
			mLabel2.setName("label2");
			this.add(mLabel2);
		}

		private MouseListener buildPrintingMouseListener(final String string)
		{
			return new MouseListener()
			{
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					System.out.println(string);
				}

				@Override
				public void mouseEntered(MouseEvent arg0)
				{
				}

				@Override
				public void mouseExited(MouseEvent arg0)
				{
				}

				@Override
				public void mousePressed(MouseEvent arg0)
				{
				}

				@Override
				public void mouseReleased(MouseEvent arg0)
				{
				}
			};
		}
	}
}
