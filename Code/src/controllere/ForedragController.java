package controllere;

import java.util.ArrayList;

import model.Foredrag;
import filewrap.ForedragSerialize;

public class ForedragController {
	private static ForedragSerialize dataAccess = new ForedragSerialize();
	private static ArrayList<Foredrag> foredrag = new ArrayList<Foredrag>();
	
	
	public static void save(Foredrag p)
	{
		if (p.getId() == 0)
		{//then it is new
			int highestId = findHighestPersonId();
			p.setId(highestId +1);
			foredrag.add(p);
		}
		
		dataAccess.save(foredrag); //write to disk.
	}
	
	
	
	private static int findHighestPersonId()
	{
		int highest = 0;
		for (Foredrag p : foredrag)
		{
			if (highest < p.getId())
				highest = p.getId();
		}
		return highest;
	}
	
	
	
	public static Foredrag findForedragById(int foredragId) {
		for (Foredrag p : foredrag)
		{
			if (p.getId() == foredragId)
			{
				return p;
			}
		}
		
		return null; //not found
	}
	
	public static ArrayList<Foredrag> getAllForedrag()
	{
		foredrag = dataAccess.load();
		return foredrag;
	}
	
	
	public static void deleteForedragById(int foredragtId)
	{
		for (int i=0; i < foredrag.size(); i++)
		{
			if (foredrag.get(i).getId() == foredragtId)
			{
				foredrag.remove(i);
				break;
			}
		}
		
		dataAccess.save(foredrag);
	}
	
	public static ArrayList<Foredrag> findForedragByName(String searchName) {
		ArrayList<Foredrag> foundForedrag = new ArrayList<Foredrag>();
		
		for (Foredrag p : foredrag)
		{
			if (p.getName().toLowerCase().contains(searchName.toLowerCase())){
				foundForedrag.add(p);
			}
		}
		
		return foundForedrag;
	}
}

