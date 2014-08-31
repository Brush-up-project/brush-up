package util;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Product;
@SuppressWarnings("serial")
public class ProductJTable extends JTable {

	public ProductJTable(ArrayList<Product> products)
	{
		updateJTable(products);
	}
	
	public void updateJTable(ArrayList<Product> products)
	{
		DefaultTableModel model = new DefaultTableModel();
	
		model.setColumnIdentifiers(new String[] {"ProductId", "Name", "Price", "Quantity"});
		model.setRowCount(products.size());
		int row = 0;
		for (Product p : products) {
			model.setValueAt(p.getProductId(), row, 0);
			model.setValueAt(p.getName(), row, 1);
			model.setValueAt(p.getPrice(), row, 2);
			model.setValueAt(p.getQuantity(), row, 3);
			row++;
		}
		setModel(model);
	}
}
