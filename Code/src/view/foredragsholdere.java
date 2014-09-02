package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import util.SpeakerJTable;
import controllere.SpeakerController;
import model.Speaker;

public class foredragsholdere extends JPanel{

	private SpeakerJTable table;
	private JTextField txtName;
	private JTextField txtPrice;
	private ArrayList<Speaker> speakersToShow;
	
	public SpeakerJTable getTable()
	{
		return table;
	}
	
	public foredragsholdere()
	{
		setLayout(new BorderLayout()); //Borderlayout on frame.
		
		speakersToShow = SpeakerController.getAllSpeakers();
		table = new SpeakerJTable(speakersToShow);
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
					table.updateJTable(SpeakerController.getAllSpeakers());
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
				new SpeakerNewEditView(foredragsholdere.this, new Speaker(), "New Product"); //send the child obj. to the new/edit view.
			}
		});
		southPanel.add(btnNew);
			
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Speaker product = findSpeakerFromSelectedRow(table.getSelectedRow());
				
				if (product == null)
					JOptionPane.showMessageDialog(foredragsholdere.this, "Internal error");
				else {
					new SpeakerNewEditView(foredragsholdere.this, product, "Edit Child"); //send the child obj. to the new/edit view.
				}
			}
		});
		southPanel.add(btnEdit);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int speakerId = findSpeakerIdFromSelectedRow(table.getSelectedRow());
				
				SpeakerController.deleteSpeakerById(speakerId);
				table.updateJTable(SpeakerController.getAllSpeakers());
				
			}
		});
		southPanel.add(btnDelete);

		add(northPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		//setTitle("Products");
		setSize(500, 300);
		setLocation(50, 50);
		setVisible(true);

	}
	
	public int findSpeakerIdFromSelectedRow(int selectedRow)
	{
		if (selectedRow == -1)
			JOptionPane.showMessageDialog(foredragsholdere.this, "Please select a row");
		
		DefaultTableModel model = (DefaultTableModel) table.getModel(); //get the model.
		int id = (int) model.getValueAt(selectedRow, 0);
		
		return id;		
	}
	
	
public Speaker findSpeakerFromSelectedRow(int selectedRow)
	{
		int speakerId = findSpeakerIdFromSelectedRow(selectedRow);
		
		Speaker product = SpeakerController.findSpeakerById(speakerId); //null is returned if not found.
		
		return product;
	}
	
}
