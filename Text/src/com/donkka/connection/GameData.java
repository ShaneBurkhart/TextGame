package com.donkka.connection;



public class GameData {
	
	private static GameData instance;
	
	public static GameData getInstance(){
		if(instance == null)
			instance = new GameData();
		return instance;
	}
	
	private GameData(){
	}
	
	private String data = "";
	
	public String getData(){
		return data;
	}
	
	public void setData(String data){
		this.data = data;
	}
}
