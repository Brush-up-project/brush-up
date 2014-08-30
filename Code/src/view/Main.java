package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class Main extends JFrame
{
	private JDesktopPane dtp = new JDesktopPane();
	JTabbedPane tabbedPane = new JTabbedPane();
    kalender firstp = new kalender();
    foredragsholdere secondp = new foredragsholdere();
    foredrag thirdp = new foredrag();
    loen fourthp = new loen();
	
	public Main()
	{
		setTitle("BrushUP");
		setSize(1200, 750);
		setLocationRelativeTo(null);
		setResizable(false);
	    setDefaultCloseOperation(EXIT_ON_CLOSE); 
        getContentPane().add(tabbedPane);
	    
	    JMenuBar menuBar = new JMenuBar();
	    
	    JMenu kalender = new JMenu("File");
	    kalender.setMnemonic(KeyEvent.VK_M);
	    menuBar.add(kalender);
	    JMenuItem newMenuItem = new JMenuItem("Logout");	    
	    kalender.add(newMenuItem);
	    
	    
	    setJMenuBar(menuBar);
	    setContentPane(tabbedPane);
	    
	    tabbedPane.add("Kalender", firstp);
        tabbedPane.add("Foredragsholder", secondp);
        tabbedPane.add("Foredrag", thirdp);
        tabbedPane.add("Løn", fourthp);
		
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
