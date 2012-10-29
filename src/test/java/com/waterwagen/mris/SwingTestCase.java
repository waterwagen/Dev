package com.waterwagen.mris;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.ComponentLookupScope;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;

public class SwingTestCase
{
	static
	{
		FailOnThreadViolationRepaintManager.install();
	}

	private Robot mRobot;

	@Before
	public void setUpRobot()
	{
		mRobot = BasicRobot.robotWithNewAwtHierarchy();
		mRobot.settings().componentLookupScope(ComponentLookupScope.ALL); // do I want this or only visible components?
	}

	@After
	public void tearDownRobot()
	{
		mRobot.cleanUp();
	}
	
	protected FrameFixture frameFixture(String frame_name)
	{
		return new FrameFixture(mRobot, frame_name);
	}
	
	protected void assertFrameVisibility(String frame_name, boolean expected_visibility)
	{
		FrameFixture frame_fixture = frameFixture(frame_name);
		assertThat(frame_fixture.target.isVisible(), is(equalTo(expected_visibility)));
	}
}