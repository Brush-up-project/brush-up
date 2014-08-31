package com.brushup.system;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import util.SpeakerJTable;
import controllere.SpeakerController;
import model.Speaker;


@SuppressWarnings("serial")
public class SpeakerView extends JFrame {
		private SpeakerJTable table;
		private JTextField txtName;
		private JTextField txtEmne;
		private ArrayList<Speaker> speakersToShow;
		
//		@Override
//	    public void keyPressed(KeyEvent e) {
//	        if (e.getKeyCode()==KeyEvent.VK_ENTER){
//	            System.out.println("Hello");
//	        }
//	        //Component frame = new JFrame();
//	        //JOptionPane.showMessageDialog(frame , "You've Submitted the name " + nameInput.getText());
//
//	    }
		
		public SpeakerJTable getTable()
		{
			return table;
		}
		
		public SpeakerView()
		{
			setLayout(new BorderLayout()); //Borderlayout on frame.
			setDefaultCloseOperation(EXIT_ON_CLOSE); // closes the application when the window closes.
			
			speakersToShow = SpeakerController.getAllSpeakers();
			table = new SpeakerJTable(speakersToShow);
			JScrollPane scrollPane = new JScrollPane(table); //add scrollpane to table
			
			JPanel northPanel = new JPanel(new FlowLayout());
			JLabel lblSearch = new JLabel("Name");
			txtName = new JTextField(10);
			JLabel lblAge = new JLabel("Emne");
			txtEmne = new JTextField(5);
			
			JButton btnSearch = new JButton("Søg");
			
			txtName.addKeyListener(new KeyAdapter() {


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
					String searchEmne = txtEmne.getText();

					
					if (searchName.equals("") && searchEmne.equals(""))
					{
						table.updateJTable(SpeakerController.getAllSpeakers());
					}
					else if (!searchName.equals("") && searchEmne.equals(""))
					{
						table.updateJTable(SpeakerController.findSpeakersByName(searchName));
					}
			
					else if (searchName.equals("") && !searchEmne.equals(""))
					{
						table.updateJTable(SpeakerController.findSpeakersByEmne(searchEmne));
					}
				}
			});
			northPanel.add(lblSearch);
			northPanel.add(txtName);
			northPanel.add(lblAge);
			northPanel.add(txtEmne);
			northPanel.add(btnSearch);

			
			JPanel southPanel = new JPanel(new FlowLayout());
			JButton btnNew = new JButton("New");
			btnNew.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new SpeakerNewEditView(SpeakerView.this, new Speaker(), "Ny Foredragsholder"); //send the child obj. to the new/edit view.
				}
			});
			southPanel.add(btnNew);
			
			
			JButton btnEdit = new JButton("Rediger");
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Speaker speaker = findSpeakerFromSelectedRow(table.getSelectedRow());
					
					if (speaker == null)
						JOptionPane.showMessageDialog(SpeakerView.this, "Internal error");
					else {
						new SpeakerNewEditView(SpeakerView.this, speaker, "Edit Child"); //send the child obj. to the new/edit view.
					}
				}
			});
			southPanel.add(btnEdit);
			
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int productId = findProductIdFromSelectedRow(table.getSelectedRow());
					
					SpeakerController.deleteProductById(productId);
					table.updateJTable(SpeakerController.getAllSpeakers());
					
				}
			});
			southPanel.add(btnDelete);

			add(northPanel, BorderLayout.NORTH);
			add(scrollPane, BorderLayout.CENTER);
			add(southPanel, BorderLayout.SOUTH);

			setTitle("Speaker");
			setSize(500, 300);
			setLocation(50, 50);
			setVisible(true);

		}
		
		public int findProductIdFromSelectedRow(int selectedRow)
		{
			if (selectedRow == -1)
				JOptionPane.showMessageDialog(SpeakerView.this, "Vælg en række");
			
			DefaultTableModel model = (DefaultTableModel) table.getModel(); //get the model.
			int id = (int) model.getValueAt(selectedRow, 0);
			
			return id;		
		}
		
		
		public Speaker findSpeakerFromSelectedRow(int selectedRow)
		{
			int speakerId = findProductIdFromSelectedRow(selectedRow);
			
			Speaker speaker = SpeakerController.findSpeakerById(speakerId); //null is returned if not found.
			
			return speaker;
		}
		
	}