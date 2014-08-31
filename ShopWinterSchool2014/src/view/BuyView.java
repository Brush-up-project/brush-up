package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Order;
import model.OrderLine;
import model.Product;
import util.OrderLineJTable;
import util.ProductJTable;
import control.OrderController;
import control.ProductController;
@SuppressWarnings("serial")
public class BuyView extends JFrame {
	private ProductJTable productsTable;
	private OrderLineJTable orderTable;
	private ArrayList<Product> productsToShow;
	private ArrayList<OrderLine> orderLines;
	private Order newOrder = new Order();
	private JButton btnSave = new JButton("Save order");
	
	public BuyView()
	{
		setLayout(new BorderLayout()); //Borderlayout on frame.

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OrderController.save(newOrder);
			}
		});
		
		productsToShow = ProductController.getAllProducts();
		productsTable = new ProductJTable(productsToShow);
		productsTable.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent e) {
			   if (e.getClickCount() == 1) {
				   try {
					   JTable target = (JTable)e.getSource();
					   int row = target.getSelectedRow();
					   int id = findIdFromSelectedRow(row);
		         
					   Product product = ProductController.findProductById(id);
					   //Ask how many you would like to buy.
					   String result = JOptionPane.showInputDialog(null, "How many " + product.getName()  +  " would you like to buy?");
					   
					   int quantity;
					   try {
						   quantity = Integer.parseInt(result);
					   }
					   catch(Exception ex)
					   {
						   JOptionPane.showInputDialog(null, "Please enter a number");
						   return;
					   }
					   
					   OrderController.addOrderLine(newOrder, product, quantity);
					   orderTable.updateJTable(newOrder.getOrderLines());
				   }
				   catch(Exception ex) {
					   JOptionPane.showInputDialog(null, "Something went wrong... (this is not a good error message)");
				   }
		      }
		   }
		});
		JScrollPane scrollPane = new JScrollPane(productsTable); //add scrollpane to table
		scrollPane.setPreferredSize(new Dimension(400,150));
		
		JPanel p1 = new JPanel();
		JLabel lblName = new JLabel("Choose products");
		p1.add(lblName);
		p1.add(btnSave);
		
		JPanel p2 = new JPanel();
		JLabel lblYourOrder = new JLabel("Your order");
		p2.add(lblYourOrder);
		
		orderLines = new ArrayList<OrderLine>();
		orderTable = new OrderLineJTable(orderLines);
		JScrollPane scrollPane2 = new JScrollPane(orderTable);
		scrollPane2.setPreferredSize(new Dimension(400,150));
		
		p2.add(scrollPane2);
		
		add(p1, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(scrollPane2, BorderLayout.SOUTH);
		
		setTitle("Buy");
		setSize(500, 300);
		setLocation(50, 50);
		setVisible(true);
	}
	
	public int findIdFromSelectedRow(int selectedRow)
	{
		if (selectedRow == -1)
			JOptionPane.showMessageDialog(BuyView.this, "Please select a row");
		
		DefaultTableModel model = (DefaultTableModel) productsTable.getModel(); //get the model.
		int id = (int) model.getValueAt(selectedRow, 0);
		
		return id;		
	}
}
