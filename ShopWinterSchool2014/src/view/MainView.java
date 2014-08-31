package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	public static void main(String[] args) {
		new MainView();
	}
	
	private JDesktopPane dtp = new JDesktopPane();
	
	public MainView()
	{
		super("Shop - Winterschool 2014");
	
		setSize(900, 600);
	    
	    //menu
	    JMenuBar  menuBar = new JMenuBar();
	    JMenu menu = new JMenu("Administration");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		
		JMenu menu2 = new JMenu("Customers");
		menu2.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu2);
	
		JMenuItem menuItem = new JMenuItem("Product Administration",KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent a)
		    {
		    	new ProductIndexView();
		    }
		});
		menu.add(menuItem);


		
		JMenuItem menuTeacher = new JMenuItem("Shop overview",KeyEvent.VK_T);
		menuTeacher.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuTeacher.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent a)
		    {
		    	new ShopOverview();
		    }
		});
		menu.add(menuTeacher);

		
		
		
		JMenuItem menuItem2 = new JMenuItem("Buy",KeyEvent.VK_T);
		menuItem2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent a)
		    {
		    	new BuyView();
		    }
		});
		menu2.add(menuItem2);
		
		setJMenuBar(menuBar);
	    setContentPane(dtp);
		setVisible(true);
	}
}
