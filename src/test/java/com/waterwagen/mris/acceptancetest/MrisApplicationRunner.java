package com.waterwagen.mris.acceptancetest;

public interface MrisApplicationRunner
{
	public void start();

	public void transformFile(String contacts_file_path);

	public void stop();
}