package filewrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Foredrag;
import model.Speaker;

public class ForedragSerialize {

	private String fileName = "foredrag.txt";
	public String save(ArrayList<Foredrag> foredrag) {
		
		String errormsg = "";	

		try
        {
			File yourFile = new File(fileName);
			yourFile.createNewFile();
			
	       FileOutputStream fileOut = new FileOutputStream(fileName, false);
	       ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
    	   		out.writeObject(foredrag);
	         
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
	
	public ArrayList<Foredrag> load() {
		ArrayList<Foredrag> groups = new ArrayList<Foredrag>();
	    try
	    {
	       FileInputStream fileIn = new FileInputStream(fileName);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       
    	   try {
    		   groups = (ArrayList<Foredrag>) in.readObject();
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
