package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class addevent extends JFrame{
		
	public addevent()
	{
		JFrame frame = new JFrame("new frame");
	    frame.setVisible(true);
	    frame.setTitle("Kalender Event");
	    frame.setSize(500, 750);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);	    

	    JPanel p = new JPanel(new BorderLayout());
	    JPanel p1 = new JPanel();
	    
	    JLabel firstName = new JLabel("Fornavn", 10);
	    JTextField firstnameText = new JTextField(10);
	    JLabel lastName = new JLabel("Efternavn", 10);
	    JTextField lastnameText = new JTextField(10);
	    JLabel subject = new JLabel ("Emne", 10);
	    JTextField subjectText = new JTextField(10);
	    JLabel date = new JLabel ("Dato", 10);
	    JTextField dateText = new JTextField(10);
	    JLabel time = new JLabel ("Tid", 10);
	    JTextField timeText = new JTextField(10);
	    JButton submit = new JButton("Submit");
	   
	    
	    p.add(firstName, BorderLayout.WEST);
	    p.add(firstnameText, BorderLayout.EAST);
	    
	    p1.add(p);
	    
	}
}
