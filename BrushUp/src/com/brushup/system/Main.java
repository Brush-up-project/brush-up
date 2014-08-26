package com.brushup.system;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Main extends JFrame
{
	private JDesktopPane dtp = new JDesktopPane();
	
	public Main()
	{
		setTitle("BrushUP");
		setSize(1200, 750);
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE); 
	    
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menu = new JMenu("Test1");
	    menu.setMnemonic(KeyEvent.VK_A);
	    menuBar.add(menu);
	    
	    JMenu menu2 = new JMenu("Test2");
	    menu.setMnemonic(KeyEvent.VK_A);
	    menuBar.add(menu2);
	    
	    setJMenuBar(menuBar);
	    setContentPane(dtp);
	    
		
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
	}

}
