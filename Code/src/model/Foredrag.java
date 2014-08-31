package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Foredrag implements Serializable {
	
	private int idForedrag;
	private String name;
	private String dato;
	private String emne;
	private String lokation;


	public int getId() {
		return idForedrag;
	}
	public void setId(int id) {
		this.idForedrag = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	public String getEmne() {
		return emne;
	}
	public void setEmne(String emne) {
		this.emne = emne;
	}
	public String getLokation()
	{
		return lokation;
	}
	public void setLokation(String lokation)
	{
		this.lokation = lokation; 
	}
	
}

