package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Speaker implements Serializable {
	
	private int id;
	private String name;
	private String emne;


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
}
