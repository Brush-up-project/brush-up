package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Order;
import util.OrderJTable;
import util.OrderLineJTable;
import control.OrderController;

@SuppressWarnings("serial")
public class ShopOverview extends JFrame {

	private OrderJTable table;
	private OrderLineJTable orderLinesTable;
	private ArrayList<Order> ordersToShow;
	
	
	public ShopOverview()
	{
		setLayout(new BorderLayout()); //Borderlayout on frame.
		
		ordersToShow = OrderController.getAllOrders();
		orderLinesTable = new OrderLineJTable();
		table = new OrderJTable(ordersToShow);
		table.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
				   if (e.getClickCount() == 1) {
					   try {
						   JTable target = (JTable)e.getSource();
						   int row = target.getSelectedRow();
						   int id = findIdFromSelectedRow(row);
			         
						   Order order = OrderController.findOrderById(id);
						   
						   orderLinesTable.updateJTable(order.getOrderLines());
					   }
					   catch(Exception ex) {
						   JOptionPane.showInputDialog(null, "Something went wrong... (this is not a good error message)");
					   }
			      }
			   }
			});
		JScrollPane scrollPane = new JScrollPane(table); //add scrollpane to table
		scrollPane.setPreferredSize(new Dimension(400,150));
		JScrollPane scrollPane2 = new JScrollPane(orderLinesTable); //add scrollpane to table
		scrollPane2.setPreferredSize(new Dimension(400,200));
		

		add(scrollPane, BorderLayout.NORTH);
		add(scrollPane2, BorderLayout.CENTER);

		setTitle("Orders");
		setSize(700, 600);
		setLocation(50, 50);
		setVisible(true);

	}
	
	public int findIdFromSelectedRow(int selectedRow)
	{
		if (selectedRow == -1)
			JOptionPane.showMessageDialog(ShopOverview.this, "Please select a row");
		
		DefaultTableModel model = (DefaultTableModel) table.getModel(); //get the model.
		int id = (int) model.getValueAt(selectedRow, 0);
		
		return id;		
	}
	
	
	public Order findOrderFromSelectedRow(int selectedRow)
	{
		int orderId = findIdFromSelectedRow(selectedRow);
		
		Order order = OrderController.findOrderById(orderId); //null is returned if not found.
		
		return order;
	}
}