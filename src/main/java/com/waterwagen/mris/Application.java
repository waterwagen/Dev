package com.waterwagen.mris;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Application
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Temp Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		JButton button = new JButton("Press");
		frame.getContentPane().add(button); // Adds Button to content pane of frame
		final JLabel label = new JLabel("N/A");
		frame.getContentPane().add(label);
		frame.setVisible(true);
	}

}
