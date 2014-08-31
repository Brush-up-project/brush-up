package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import util.ProductJTable;
import control.ProductController;
import model.Product;


@SuppressWarnings("serial")
public class ProductIndexView extends JFrame {
		private ProductJTable table;
		private JTextField txtName;
		private JTextField txtPrice;
		private ArrayList<Product> productsToShow;
		
//		@Override
//	    public void keyPressed(KeyEvent e) {
//	        if (e.getKeyCode()==KeyEvent.VK_ENTER){
//	            System.out.println("Hello");
//	        }
//	        //Component frame = new JFrame();
//	        //JOptionPane.showMessageDialog(frame , "You've Submitted the name " + nameInput.getText());
//
//	    }
		
		public ProductJTable getTable()
		{
			return table;
		}
		
		public ProductIndexView()
		{
			setLayout(new BorderLayout()); //Borderlayout on frame.
			setDefaultCloseOperation(EXIT_ON_CLOSE); // closes the application when the window closes.
			
			productsToShow = ProductController.getAllProducts();
			table = new ProductJTable(productsToShow);
			JScrollPane scrollPane = new JScrollPane(table); //add scrollpane to table
			
			JPanel northPanel = new JPanel(new FlowLayout());
			JLabel lblSearch = new JLabel("Name");
			txtName = new JTextField(10);
			JLabel lblAge = new JLabel("Max. Price");
			txtPrice = new JTextField(5);
			
			JButton btnSearch = new JButton("Search");
			
			txtName.addKeyListener(new KeyAdapter() {

			    @Override
			    public void keyTyped(KeyEvent e) {
		    		    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			            // Do stuff
			        }
			    }
			});

			btnSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//find out which controller method to call
					String searchName = txtName.getText();
					double searchPrice = 0;
					try {
						searchPrice = Double.parseDouble(txtPrice.getText());
					} catch (Exception NumberFormatException) {}
					
					
					if (searchName.equals("") && searchPrice == 0)
					{
						table.updateJTable(ProductController.getAllProducts());
					}
					else if (!searchName.equals("") && searchPrice == 0)
					{
						table.updateJTable(ProductController.findProductsByName(searchName));
					}
					else if (searchName.equals("") && searchPrice != 0)
					{
						table.updateJTable(ProductController.findProductsByPrice(searchPrice));
					}
				}
			});
			northPanel.add(lblSearch);
			northPanel.add(txtName);
			northPanel.add(lblAge);
			northPanel.add(txtPrice);
			northPanel.add(btnSearch);

			
			JPanel southPanel = new JPanel(new FlowLayout());
			JButton btnNew = new JButton("New");
			btnNew.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new ProductNewEditView(ProductIndexView.this, new Product(), "New Product"); //send the child obj. to the new/edit view.
				}
			});
			southPanel.add(btnNew);
			
			
			JButton btnEdit = new JButton("Edit");
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Product product = findProductFromSelectedRow(table.getSelectedRow());
					
					if (product == null)
						JOptionPane.showMessageDialog(ProductIndexView.this, "Internal error");
					else {
						new ProductNewEditView(ProductIndexView.this, product, "Edit Child"); //send the child obj. to the new/edit view.
					}
				}
			});
			southPanel.add(btnEdit);
			
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int productId = findProductIdFromSelectedRow(table.getSelectedRow());
					
					ProductController.deleteProductById(productId);
					table.updateJTable(ProductController.getAllProducts());
					
				}
			});
			southPanel.add(btnDelete);

			add(northPanel, BorderLayout.NORTH);
			add(scrollPane, BorderLayout.CENTER);
			add(southPanel, BorderLayout.SOUTH);

			setTitle("Products");
			setSize(500, 300);
			setLocation(50, 50);
			setVisible(true);

		}
		
		public int findProductIdFromSelectedRow(int selectedRow)
		{
			if (selectedRow == -1)
				JOptionPane.showMessageDialog(ProductIndexView.this, "Please select a row");
			
			DefaultTableModel model = (DefaultTableModel) table.getModel(); //get the model.
			int id = (int) model.getValueAt(selectedRow, 0);
			
			return id;		
		}
		
		
		public Product findProductFromSelectedRow(int selectedRow)
		{
			int productId = findProductIdFromSelectedRow(selectedRow);
			
			Product product = ProductController.findProductById(productId); //null is returned if not found.
			
			return product;
		}
		
	}
