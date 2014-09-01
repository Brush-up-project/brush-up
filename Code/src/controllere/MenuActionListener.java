package controllere;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.login;

public class MenuActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		{
			if(login.GetInstance().IsLoggedIn())
			{
//				this.button_login_logout.setText("Log Ind");
				JOptionPane.showMessageDialog(null, "Du er nu logget ud");
				login.GetInstance().Logout();
				return;
				
			}
			JPanel userPanel = new JPanel();
	        userPanel.setLayout(new GridLayout(2,2));
	        
	        JLabel brugernavnLabel = new JLabel("Brugernavn:");
	        JLabel passwordLabel = new JLabel("Password:");
	        JTextField brugernavn = new JTextField();
	        JPasswordField passwordFld = new JPasswordField();
	        
	        userPanel.add(brugernavnLabel);
	        userPanel.add(brugernavn);
	        userPanel.add(passwordLabel);
	        userPanel.add(passwordFld);
	        
	        brugernavn.requestFocusInWindow();
	        
	        int n = JOptionPane.showConfirmDialog(null,userPanel, "BrushUp"
	                ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        if(n == JOptionPane.CANCEL_OPTION)
	        {
	        	
	        }
	        else
	        {
	        	String enteredUsername = brugernavn.getText();
	        	char[] enteredPassword = passwordFld.getPassword();
	        	String enteredPassString = new String(enteredPassword);
	        
	        	if(login.GetInstance().Login(enteredUsername, enteredPassString))
	        	{
	        		JOptionPane.showMessageDialog(null, "Du er nu logget ind i BrushUp");
//	        		this.button_login_logout.setText("Log Ud");
	        	}
	        	else{
	        	JOptionPane.showMessageDialog(null, "Ugyldigt brugernavn eller password");
	        	}
	        }

	}

	}
	}
