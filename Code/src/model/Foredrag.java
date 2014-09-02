package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Foredrag implements Serializable {
	
	private int id;
	private String name;
	private String emne;
	private String date;
	private String location;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmne() {
		return emne;
	}
	public void setEmne(String emne) {
		this.emne = emne;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation(){
		return location;
	}
	public void setLocation(String location){
		this.location = location;
	}
}

