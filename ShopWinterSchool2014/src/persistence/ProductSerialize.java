package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Product;


public class ProductSerialize {

	private String filePath = "C:\\Users\\Desktop\\Desktop\\";
	private String fileName = "product.ser";
	
	public String save(ArrayList<Product> products) {
		String errormsg = "";	

		try
        {
			File yourFile = new File(filePath + fileName);
			yourFile.createNewFile();
			
	       FileOutputStream fileOut = new FileOutputStream(filePath + fileName, false);
	       ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
    	   		out.writeObject(products);
	         
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
	public ArrayList<Product> load() {
		ArrayList<Product> groups = new ArrayList<Product>();
	    try
	    {
	       FileInputStream fileIn = new FileInputStream(filePath + fileName);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       
    	   try {
    		   groups = (ArrayList<Product>) in.readObject();
    	   }
    	   catch(Exception e)
    	   {
    	
    	   }
       
	       in.close();
	       fileIn.close();
	    }catch(IOException i)
	    {
	       return groups;
	    }
		
		return groups;
	}
}
