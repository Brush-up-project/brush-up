package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.Order;


public class OrderSerialize {

	private String filePath = "C:\\Users\\Desktop\\Desktop\\";
	// Mac example filepath: /Users/me/Documents/test.txt 
	private String fileName = "order.ser";
	
	public String save(ArrayList<Order> orders) {
		String errormsg = "";	

		try
        {
			File yourFile = new File(filePath + fileName);
			yourFile.createNewFile();
			
	       FileOutputStream fileOut = new FileOutputStream(filePath + fileName, false);
	       ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
    	   out.writeObject(orders);
	         
	       out.close();
	       fileOut.close();
	    }
		catch(IOException i)
	    {
			errormsg = "Could not access the file";
	        i.printStackTrace();
	    }
		return errormsg;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Order> load() {
		ArrayList<Order> orders = new ArrayList<Order>();
	    try
	    {
	       FileInputStream fileIn = new FileInputStream(filePath + fileName);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       
    	   try {
    		   orders = (ArrayList<Order>) in.readObject();
    	   }
    	   catch(Exception e)
    	   {
    	
    	   }
       
	       in.close();
	       fileIn.close();
	    }catch(IOException i)
	    {
	       return orders;
	    }
		
		return orders;
	}
}
