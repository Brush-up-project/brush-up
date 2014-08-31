package controllere;

import java.util.ArrayList;

import model.Speaker;
import filewrap.SpeakerSerialize;

public class SpeakerController {
	
	private static ArrayList<Speaker> speakers = new ArrayList<Speaker>();
	private static SpeakerSerialize dataAccess = new SpeakerSerialize();
	
	public static void save(Speaker s)
	{
		if(s.getId() == 0)
		{
			int highestId = findHighestPersonId();
			s.setId(highestId +1);
			speakers.add(s);
		}
	}
	
	// sørger for at ny foredragsholder får det korrekte nye ID
	public static int findHighestPersonId()
	{
		int highest = 0;
		for (Speaker s : speakers)
		{
			if (highest < s.getId())
				highest = s.getId();
		}
		return highest;
	}
	
	public static ArrayList<Speaker> getAllSpeakers()
	{
		speakers = dataAccess.load();
		return speakers;
	}
	
	
	public static void deleteProductById(int productId)
	{
		for (int i=0; i < speakers.size(); i++)
		{
			if (speakers.get(i).getId() == productId)
			{
				speakers.remove(i);
				break;
			}
		}
		
		dataAccess.save(speakers);
	}
	
	// til søgefunktion i fordragsholdervinduet 
	
	public static ArrayList<Speaker> findSpeakersByName(String searchName) 
	{
		
		ArrayList<Speaker> foundSpeakers = new ArrayList<Speaker>();
		
		for (Speaker s : speakers)
		{
			if (s.getName().toLowerCase().contains(searchName.toLowerCase())){
				foundSpeakers.add(s);
			}
		}
		
		return foundSpeakers;
	}
	
	// søgefunktion på foredragholders emne
	
	public static ArrayList<Speaker> findSpeakersByEmne(String searchEmne) 
	{
		
		ArrayList<Speaker> foundSpeakers = new ArrayList<Speaker>();
		
		for (Speaker s : speakers)
		{
			if (s.getEmne().toLowerCase().contains(searchEmne.toLowerCase())){
				foundSpeakers.add(s);
			}
		}
		
		return foundSpeakers;
	}
	public static Speaker findSpeakerById(int Id) 
	{
		for (Speaker s : speakers)
		{
			if (s.getId() == Id)
			{
				return s;
			}
		}
		
		return null; //not found
	}
}
