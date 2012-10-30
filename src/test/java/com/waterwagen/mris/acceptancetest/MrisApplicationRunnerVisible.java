package com.waterwagen.mris.acceptancetest;

import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JTextComponentFixture;

import com.waterwagen.mris.MrisMainWindow;

public class MrisApplicationRunnerVisible extends AbstractMrisApplicationRunner
{
	@Override
	public void start()
	{
		super.start();
		frameFixture().show();
//		((MrisMainWindow)frameFixture().target).display();
	}

	@Override
	public void transformFile(String contacts_file_path)
	{
		frameFixture().textBox().setText(contacts_file_path);
		frameFixture().button().click();
	}
}
