package util;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Speaker;

@SuppressWarnings("serial")
public class SpeakerJTable extends JTable {

	public SpeakerJTable(ArrayList<Speaker> speakers)
	{
		updateJTable(speakers);
	}
	
	public void updateJTable(ArrayList<Speaker> speakers)
	{
		DefaultTableModel model = new DefaultTableModel();
	
		model.setColumnIdentifiers(new String[] {"Navn", "Emne", "ID"});
		model.setRowCount(speakers.size());
		int row = 0;
		for (Speaker s : speakers) {
			model.setValueAt(s.getName(), row, 0);
			model.setValueAt(s.getEmne(), row, 1);
			model.setValueAt(s.getId(), row, 2);

			row++;
		}
		setModel(model);
	}
}
