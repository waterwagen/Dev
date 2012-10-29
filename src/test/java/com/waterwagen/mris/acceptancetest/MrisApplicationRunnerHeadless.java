package com.waterwagen.mris.acceptancetest;

import static com.waterwagen.mris.TestUtils.clickButtonInFrame;
import static com.waterwagen.mris.TestUtils.setTextInTextBoxInFrame;

public class MrisApplicationRunnerHeadless extends AbstractMrisApplicationRunner
{
	@Override
	public void transformFile(String contacts_file_path)
	{
		setTextInTextBoxInFrame(frameFixture(), contacts_file_path);		
		clickButtonInFrame(frameFixture());
	}
}
