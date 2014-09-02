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
public class ForedragNewEditView extends JFrame implements ActionListener {
	private JTextField txtName;
	private JTextField txtEmne;
	private JTextField txtDate;
	private JTextField txtLocation;
	private JTextField txtId;
	private Foredrag entity;
	private foredrag mainView;
	
	private JButton btnSave = new JButton("Save");
	
	public ForedragNewEditView(final foredrag mainView, Foredrag entity, String header)
	{
		this.entity = entity;
		this.mainView = mainView;
		
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
		
		JLabel lblDate = new JLabel("Dato");
		txtDate = new JTextField(15);
		p1.add(lblDate);
		p1.add(txtDate);
		
		JLabel lblLocation = new JLabel("Lokation");
		txtLocation = new JTextField(15);
		p1.add(lblLocation);
		p1.add(txtLocation);
		
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
		txtDate.setText("" + entity.getDate());
		txtLocation.setText("" + entity.getLocation());
		txtId.setText("" + entity.getId());
	}
	
	private Foredrag getViewData() throws WrongNumberException
	{
		String name  = txtName.getText();
		String emne = txtEmne.getText();
		String date = txtDate.getText();
		String location = txtLocation.getText();
		int id = 0;

		entity.setName(name);
		entity.setEmne(emne);
		entity.setDate(date);
		entity.setLocation(location);
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
