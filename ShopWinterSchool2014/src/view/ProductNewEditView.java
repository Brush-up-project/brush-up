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

import util.WrongNumberException;
import model.Product;
import control.ProductController;

@SuppressWarnings("serial")
public class ProductNewEditView extends JFrame implements ActionListener {
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private Product entity;
	private ProductIndexView mainView;
	
	private JButton btnSave = new JButton("Save");
	
	public ProductNewEditView(final ProductIndexView mainView, Product entity, String header)
	{
		this.entity = entity;
		this.mainView = mainView;
		
		setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		JLabel lblName = new JLabel("Name");
		txtName = new JTextField(15);
		p1.add(lblName);
		p1.add(txtName);
		
		JLabel lblPrice = new JLabel("Price");
		txtPrice = new JTextField(15);
		p1.add(lblPrice);
		p1.add(txtPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		txtQuantity = new JTextField(15);
		p1.add(lblQuantity);
		p1.add(txtQuantity);
		
		
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
	public void loadData(Product entity)
	{
		txtName.setText(entity.getName());
		txtPrice.setText("" + entity.getPrice());
		txtQuantity.setText("" + entity.getQuantity());
	}
	
	private Product getViewData() throws WrongNumberException
	{
		String name  = txtName.getText();
		double price;
		try {
			price  = Double.parseDouble(txtPrice.getText());
		}
		catch(Exception e) {
			throw new WrongNumberException(txtPrice.getText());
		}
		
		int quantity;
		try {
			quantity = Integer.parseInt(txtQuantity.getText());
		}
		catch (Exception e) {
			throw new WrongNumberException(txtPrice.getText());
		}
		
		
		entity.setName(name);
		entity.setPrice(price);
		entity.setQuantity(quantity);
		
		return entity;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnSave)
		{
			try {
				Product entityToSave = getViewData();
						
				ProductController.save(entityToSave);
				mainView.getTable().updateJTable(ProductController.getAllProducts());
				this.dispose();
			}
			catch (WrongNumberException ex)
			{
				JOptionPane.showMessageDialog(null, "Must enter a number in price field. You entered " + ex.getMessage());
			}
		}
	}
}