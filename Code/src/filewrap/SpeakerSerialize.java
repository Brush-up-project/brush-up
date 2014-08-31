package filewrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Speaker;

//import model.Product;


public class SpeakerSerialize {

	private String filePath = "C:\\Users\\Desktop\\Desktop\\";
	private String fileName = "Speakers.txt";
	
	public String save(ArrayList<Speaker> speakers) {
		
		String errormsg = "";	

		try
        {
			File yourFile = new File(filePath + fileName);
			yourFile.createNewFile();
			
	       FileOutputStream fileOut = new FileOutputStream(filePath + fileName, false);
	       ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
    	   		out.writeObject(speakers);
	         
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
	
	public ArrayList<Speaker> load() {
		ArrayList<Speaker> groups = new ArrayList<Speaker>();
	    try
	    {
	       FileInputStream fileIn = new FileInputStream(filePath + fileName);
	       ObjectInputStream in = new ObjectInputStream(fileIn);
	       
    	   try {
    		   groups = (ArrayList<Speaker>) in.readObject();
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
