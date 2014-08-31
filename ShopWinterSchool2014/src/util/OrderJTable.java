package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Order;
@SuppressWarnings("serial")
public class OrderJTable extends JTable {
	public OrderJTable(ArrayList<Order> orders)
	{
		updateJTable(orders);
	}
	
	public void updateJTable(ArrayList<Order> orders)
	{
		DefaultTableModel model = new DefaultTableModel();

		SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
		
		model.setColumnIdentifiers(new String[] {"OrderId", "Order Date"});
		model.setRowCount(orders.size());
		int row = 0;
		for (Order o : orders) {
			model.setValueAt(o.getOrderId(), row, 0);
			model.setValueAt(s.format(o.getOrderDate()), row, 1);
			row++;
		}
		setModel(model);
	}
}