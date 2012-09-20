package com.waterwagen;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author waterwagen
 *
 */
public class Sandbox
{
	public static void main(String[] argv) throws IOException
	{
		final JFrame frame = new JFrame("Temp Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		JButton button = new JButton("Press");
		frame.getContentPane().add(button); // Adds Button to content pane of frame
		final JLabel label = new JLabel("N/A");
		frame.getContentPane().add(label);
		frame.setVisible(true);
		Thread label_setter = new Thread()
		{
			@Override
			public void run()
			{
				try{Thread.sleep(5_000);} catch (InterruptedException exc){exc.printStackTrace();}
				label.setText("Another thread set this text.");	
			}
		};
		label_setter.start();
	}
		
	public void printString() 
	{
		System.out.println("this is a test string println'd from " + this.getClass().getName());
	}
}
