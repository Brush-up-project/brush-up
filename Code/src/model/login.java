package model;

import model.login;

public class login {
	
	private static login instance;
	
	private boolean IsLoggedIn;
	
	private boolean validering;
	
	public static login GetInstance()
	{
		if(instance == null)
		{
			instance = new login();
		}
		return instance;

	}
	public boolean Login(String username, String password)
	{
		String admin 	= "john";
		String speaker 	= "per";
		String kode 	= "john";
		String kode2 	= "per";
		
		boolean validering = password.equals(kode) && username.equalsIgnoreCase(admin) || password.equals(kode2) && username.equalsIgnoreCase(speaker);
		
		if(validering)
		{
			this.IsLoggedIn = true;
		}
		else
		{
			this.IsLoggedIn = false;
		}	
		return IsLoggedIn;
		
	}
	public boolean IsLoggedIn()
	{
		return this.IsLoggedIn;
	}
	public void Logout()
	{
		this.IsLoggedIn = false;
	}
}
