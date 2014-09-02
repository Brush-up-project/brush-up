package controllere;

import java.util.ArrayList;

import model.Speaker;
import filewrap.SpeakerSerialize;

public class SpeakerController {
	private static SpeakerSerialize dataAccess = new SpeakerSerialize();
	private static ArrayList<Speaker> speakers = new ArrayList<Speaker>();
	
	
	public static void save(Speaker p)
	{
		if (p.getId() == 0)
		{//then it is new
			int highestId = findHighestPersonId();
			p.setId(highestId +1);
			speakers.add(p);
		}
		
		dataAccess.save(speakers); //write to disk.
	}
	
	
	
	private static int findHighestPersonId()
	{
		int highest = 0;
		for (Speaker p : speakers)
		{
			if (highest < p.getId())
				highest = p.getId();
		}
		return highest;
	}
	
	
	
	public static Speaker findSpeakerById(int speakerId) {
		for (Speaker p : speakers)
		{
			if (p.getId() == speakerId)
			{
				return p;
			}
		}
		
		return null; //not found
	}
	
	public static ArrayList<Speaker> getAllSpeakers()
	{
		speakers = dataAccess.load();
		return speakers;
	}
	
	
	public static void deleteSpeakerById(int speakerId)
	{
		for (int i=0; i < speakers.size(); i++)
		{
			if (speakers.get(i).getId() == speakerId)
			{
				speakers.remove(i);
				break;
			}
		}
		
		dataAccess.save(speakers);
	}
	
	public static ArrayList<Speaker> findProductsByName(String searchName) {
		ArrayList<Speaker> foundProducts = new ArrayList<Speaker>();
		
		for (Speaker p : speakers)
		{
			if (p.getName().toLowerCase().contains(searchName.toLowerCase())){
				foundProducts.add(p);
			}
		}
		
		return foundProducts;
	}
}

