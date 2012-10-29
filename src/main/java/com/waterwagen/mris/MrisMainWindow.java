package com.waterwagen.mris;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.google.inject.Inject;

@SuppressWarnings("serial")
public class MrisMainWindow extends JFrame
{
	private JTextField mFileInput;
	private JButton mProcessContacts;
	private TransformRequestListener mTransformRequestListener;

	public MrisMainWindow() throws HeadlessException
	{
		super();
		
		mFileInput = new JTextField();
		mFileInput.setName("inputFilePathTextBox");
		getContentPane().add(mFileInput);
		
		mProcessContacts = new JButton();
		mProcessContacts.setName("submitTransformRequestButton");
		mProcessContacts.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					mTransformRequestListener.transformRequest(FileUtils.readFile(mFileInput.getText()));
				} 
				catch (IOException exc)
				{
					throw new RuntimeException(exc);
				}
			}	
		});
		getContentPane().add(mProcessContacts);

		setSize(100, 100);
		setName(Main.MAIN_WINDOW_COMPONENT_NAME);
	}

	public void display()
	{
		setVisible(true);
	}

	@Inject
	public void addTransformRequestListener(TransformRequestListener listener)
	{
		this.mTransformRequestListener = listener;
	}
}