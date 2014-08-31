package util;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Foredrag;

@SuppressWarnings("serial")
public class ForedragJTable extends JTable {

	public ForedragJTable(ArrayList<Foredrag> foredrag)
	{
		updateJTable(foredrag);
	}
	
	public void updateJTable(ArrayList<Foredrag> foredrag)
	{
		DefaultTableModel model = new DefaultTableModel();
	
		model.setColumnIdentifiers(new String[] {"Navn", "Dato","Emne", "Lokation", "ID"});
		model.setRowCount(foredrag.size());
		int row = 0;
		for (Foredrag f : foredrag) {
			model.setValueAt(f.getName(), row, 0);
			model.setValueAt(f.getDato(), row, 1);
			model.setValueAt(f.getEmne(), row, 2);
			model.setValueAt(f.getLokation(), row, 3);
			model.setValueAt(f.getId(), row, 4);

			row++;
		}
		setModel(model);
	}
}
