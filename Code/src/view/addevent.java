package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addevent extends JFrame{
	
	JFrame f1;
	JLabel lblfirstName,lbllastName,lblCity,lblState,lblMail;
	JTextField txtfirstName,txtlastName,txtCity,txtState,txtMail;
	JButton btnClear,btnClose,btnSave,btnDelete,btnUpdate,btnView;
	JPanel p1,p2;
	GridLayout gl42,gl21;
	FlowLayout fl;
		
	public addevent()
	{
		f1=new JFrame("Add Event");

		lblfirstName=new JLabel("Fornavn :");
		lbllastName=new JLabel("Efternavn :");
		lblCity=new JLabel("Foredragsemne :");
		lblState=new JLabel("Dato :");
		lblMail=new JLabel("Tid :");
		
		txtfirstName=new JTextField(40);
		txtlastName=new JTextField(40);
		txtCity=new JTextField(40);
		txtState=new JTextField(40);
		txtMail=new JTextField(40);

		btnClose=new JButton("Close");
		btnSave=new JButton("Save");

		p1=new JPanel();
		p2=new JPanel();

		gl42=new GridLayout(5,2);
		gl21=new GridLayout(2,1);
		fl=new FlowLayout();

		p1.setLayout(gl42);
		p1.add(lblfirstName);
		p1.add(txtfirstName);
		p1.add(lbllastName);
		p1.add(txtlastName);
		p1.add(lblCity);
		p1.add(txtCity);
		p1.add(lblState);
		p1.add(txtState);
		p1.add(lblMail);
		p1.add(txtMail);

		p2.setLayout(fl);
		p2.add(btnSave);
		p2.add(btnClose);
		
		f1.setLayout(gl21);
		f1.add(p1);
		f1.add(p2);
		f1.setSize(400,200);
		f1.setVisible(true);
		f1.setLocationRelativeTo(null);
	}
}
