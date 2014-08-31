package controllere;

import java.util.ArrayList;

import model.Foredrag;
import filewrap.ForedragSerialize;

public class ForedragController {
	
	private static ArrayList<Foredrag> foredragListe = new ArrayList<Foredrag>();
	private static ForedragSerialize dataAccess2 = new ForedragSerialize();
	
	public static void save(Foredrag s)
	{
		if(s.getId() == 0)
		{
			int highestId = findHighestId();
			s.setId(highestId +1);
			foredragListe.add(s);
		}
	}
	
	// sørger for at nyt foredrag får det korrekte nye ID
	public static int findHighestId()
	{
		int highest = 0;
		for (Foredrag s : foredragListe)
		{
			if (highest < s.getId())
				highest = s.getId();
		}
		return highest;
	}
	
	public static ArrayList<Foredrag> getAllForedrag()
	{
		foredragListe = dataAccess2.load();
		return foredragListe;
	}
	
	
	public static void deleteProductById(int productId)
	{
		for (int i=0; i < foredragListe.size(); i++)
		{
			if (foredragListe.get(i).getId() == productId)
			{
				foredragListe.remove(i);
				break;
			}
		}
		
		dataAccess2.save(foredragListe);
	}
	
	// til søgefunktion i fordragsholdervinduet 
	
	public static ArrayList<Foredrag> findForedragByName(String searchName) {
		
		ArrayList<Foredrag> foundForedrag = new ArrayList<Foredrag>();
		
		for (Foredrag f : foredragListe)
		{
			if (f.getName().toLowerCase().contains(searchName.toLowerCase())){
				foundForedrag.add(f);
			}
		}
		
		return foundForedrag;
	}
	
	// søgefunktion på foredragets dato
	
	public static ArrayList<Foredrag> findForedragByDato(String searchDato) {
		
		ArrayList<Foredrag> foundForedrag = new ArrayList<Foredrag>();
		
		for (Foredrag f : foredragListe)
		{
			if (f.getDato().contains(searchDato))
			{
				foundForedrag.add(f);
			}
		}
		
		return foundForedrag;
	}

}