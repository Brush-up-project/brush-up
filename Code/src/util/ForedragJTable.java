package util;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Foredrag;
import model.Speaker;

@SuppressWarnings("serial")
public class ForedragJTable extends JTable {

	public ForedragJTable(ArrayList<Foredrag> foredrag)
	{
		updateJTable(foredrag);
	}
	
	public void updateJTable(ArrayList<Foredrag> foredrag)
	{
		DefaultTableModel model = new DefaultTableModel();
	
		model.setColumnIdentifiers(new String[] {"Navn", "Emne","Dato", "Lokation", "ID"});
		model.setRowCount(foredrag.size());
		int row = 0;
		for (Foredrag s : foredrag) {
			model.setValueAt(s.getName(), row, 0);
			model.setValueAt(s.getEmne(), row, 1);
			model.setValueAt(s.getDate(), row, 2);
			model.setValueAt(s.getLocation(), row, 3);
			model.setValueAt(s.getId(), row, 4);

			row++;
		}
		setModel(model);
	}
}
