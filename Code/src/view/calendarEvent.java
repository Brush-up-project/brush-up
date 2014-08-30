package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class calendarEvent extends JFrame{
	
	public calendarEvent()
	{
		JFrame frame = new JFrame("new frame");
        frame.setVisible(true);
        frame.setTitle("Kalender Event");
        frame.setSize(500, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(0,1,1,1));
        JLabel noEvent = new JLabel("Der er ingen kalenderbegivenhed");
        JButton addEvent = new JButton("Tilføj nyt event");
        addEvent.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new addevent();
            }
        });
        frame.add(addEvent);
        frame.add(noEvent);
        
	}
	
}
