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

import util.ForedragJTable;
import controllere.ForedragController;
import model.Foredrag;

public class foredrag extends JPanel{

	private ForedragJTable table;
	private JTextField txtName;
	private JTextField txtPrice;
	private ArrayList<Foredrag> foredragToShow;
	
	public ForedragJTable getTable()
	{
		return table;
	}
	
	public foredrag()
	{
		setLayout(new BorderLayout()); //Borderlayout on frame.
		
		foredragToShow = ForedragController.getAllForedrag();
		table = new ForedragJTable(foredragToShow);
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
					table.updateJTable(ForedragController.getAllForedrag());
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
				new ForedragNewEditView(foredrag.this, new Foredrag(), "New Product"); //send the child obj. to the new/edit view.
			}
		});
		southPanel.add(btnNew);
			
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Foredrag foredrag = findForedragFromSelectedRow(table.getSelectedRow());
				
				if (foredrag == null)
					JOptionPane.showMessageDialog(foredrag.this, "Internal error");
				else {
					new ForedragNewEditView(foredrag.this, foredrag, "Edit Child"); //send the child obj. to the new/edit view.
				}
			}
		});
		southPanel.add(btnEdit);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int foredragId = findForedragIdFromSelectedRow(table.getSelectedRow());
				
				ForedragController.deleteForedragById(foredragId);
				table.updateJTable(ForedragController.getAllForedrag());
								
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
	
	public int findForedragIdFromSelectedRow(int selectedRow)
	{
		if (selectedRow == -1)
			JOptionPane.showMessageDialog(foredrag.this, "Please select a row");
		
		DefaultTableModel model = (DefaultTableModel) table.getModel(); //get the model.
		int id = (int) model.getValueAt(selectedRow, 0);
		
		return id;		
	}
	
	
	public Foredrag findForedragFromSelectedRow(int selectedRow)
	{
		int productId = findForedragIdFromSelectedRow(selectedRow);
		
		Foredrag foredrag = ForedragController.findForedragById(productId); //null is returned if not found.
		
		return foredrag;
	}
	
}
