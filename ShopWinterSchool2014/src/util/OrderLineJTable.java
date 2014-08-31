package util;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.OrderLine;
@SuppressWarnings("serial")
public class OrderLineJTable extends JTable {

	public OrderLineJTable()
	{}
	public OrderLineJTable(ArrayList<OrderLine> orders)
	{
		updateJTable(orders);
	}
	
	public void updateJTable(ArrayList<OrderLine> orderLines)
	{
		DefaultTableModel model = new DefaultTableModel();
	
		model.setColumnIdentifiers(new String[] {"ProductId", "Name", "Price", "Quantity", "Subtotal"});
		model.setRowCount(orderLines.size());
		int row = 0;
		for (OrderLine o : orderLines) {
			model.setValueAt(o.getProduct().getProductId(), row, 0);
			model.setValueAt(o.getProduct().getName(), row, 1);
			model.setValueAt(o.getProduct().getPrice(), row, 2);
			model.setValueAt(o.getQuantity(), row, 3);
			model.setValueAt(o.getQuantity() * o.getProduct().getPrice(), row, 4);
			row++;
		}
		setModel(model);
	}
}
