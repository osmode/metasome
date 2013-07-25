package com.logisome.metasome;


//Class 'mindPanel' corresponds to "mindFragment" 
public class MindPanel {
	
	//id and timestamp and relevant for retrieving SQLite records
	//but not for saving new records from the fragment
	private int id = 0;
	private long timestamp = 0;
	
	private int sleep = 0;
	private int mood = 0;
	private int anxiety = 0;
	private int energy = 0;
	private int concentration = 0;
	private int appetite = 0;
	private int libido = 0;
	
	
	public MindPanel(int newSleep, int newMood, int newAnxiety, 
			int newEnergy, int newConcentration, int newAppetite, int newLibido) {

			setAll(newSleep, newMood, newAnxiety, newEnergy, newConcentration,
				newAppetite, newLibido);
			
			timestamp = System.currentTimeMillis() / 1000L;
	}
	
	public void setAll (int newSleep, int newMood, int newAnxiety, 
		int newEnergy, int newConcentration, int newAppetite, int newLibido) {
		
		sleep = newSleep;
		mood = newMood;
		anxiety = newAnxiety;
		energy = newEnergy;
		concentration = newConcentration;
		appetite = newAppetite;
		libido = newLibido;
		
	}
	
	//Getters - accessed via logDatabaseHelper class 
	public int getId() {
		return id;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public int getSleep() {
		return sleep;
	}
	
	public int getMood() {
		return mood;
	}
	
	public int getAnxiety() {
		return anxiety;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public int getConcentration() {
		return concentration;  
	}
	
	public int getAppetite() {
		return appetite;
	}
	
	public int getLibido() {
		return libido;
	}
	
}
