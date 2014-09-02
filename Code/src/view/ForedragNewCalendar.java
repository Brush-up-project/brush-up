package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Foredrag;
import util.WrongNumberException;
import controllere.ForedragController;

@SuppressWarnings("serial")
public class ForedragNewCalendar extends JFrame implements ActionListener {
	private JTextField txtName;
	private JTextField txtEmne;
	private JTextField txtId;
	private Foredrag entity;
	private calendarEvent mainView;
	
	private JButton btnSave = new JButton("Save");
	
	public ForedragNewCalendar(final calendarEvent calendarEvent, Foredrag entity, String header)
	{
		this.entity = entity;
		this.mainView = calendarEvent;
		
		setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		JLabel lblName = new JLabel("Navn");
		txtName = new JTextField(15);
		p1.add(lblName);
		p1.add(txtName);
		
		JLabel lblEmne = new JLabel("Emne");
		txtEmne = new JTextField(15);
		p1.add(lblEmne);
		p1.add(txtEmne);
		
		JLabel lblId = new JLabel("Id");
		txtId = new JTextField(15);
		p1.add(lblId);
		p1.add(txtId);
		
		
		btnSave.addActionListener(this); 
		
		JPanel center = new JPanel(new GridLayout(2,2));
		center.add(p1);
	
		JPanel south = new JPanel();
		south.add(btnSave);
		
		
		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.CENTER);
		
		setTitle("Product");
		setLocation(0, 0);
		setVisible(true);
		pack();
        
        loadData(entity);
	}

	//load data from obj. to view
	public void loadData(Foredrag entity)
	{
		txtName.setText(entity.getName());
		txtEmne.setText("" + entity.getEmne());
		txtId.setText("" + entity.getId());
	}
	
	private Foredrag getViewData() throws WrongNumberException
	{
		String name  = txtName.getText();
		String price = txtEmne.getText();
		int id = 0;

		entity.setName(name);
		entity.setEmne(price);
		entity.setId(id);
		
		return entity;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnSave)
		{
			try {
				Foredrag entityToSave = getViewData();
						
				ForedragController.save(entityToSave);
				mainView.getTable().updateJTable(ForedragController.getAllForedrag());
				this.dispose();
			}
			catch (WrongNumberException ex)
			{
				JOptionPane.showMessageDialog(null, "Must enter a number in price field. You entered " + ex.getMessage());
			}
		}
	}
}
