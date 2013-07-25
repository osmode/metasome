package com.logisome.metasome;

//Class 'bodyPanel' corresponds to the "metabolicFragment"
public class BodyPanel {
	
	//id is relevant when querying the SQLite database, not for creating entries
	private int id;
	private long timestamp;
	private int hr = 0;
	private int systolic = 0;
	private int diastolic = 0;
	private int weight = 0;
	private int glucose = 0;
	
	public BodyPanel(int newHr, int newSystolic, int newDiastolic, int newWeight, int newGlucose) {
		timestamp = System.currentTimeMillis() / 1000L;
		setAll(newHr, newSystolic, newDiastolic, newWeight, newGlucose);
	}
	
	public void setAll(int newHr, int newSystolic, int newDiastolic, 
		int newWeight, int newGlucose) {
		
		hr = newHr;
		systolic = newSystolic;
		diastolic = newDiastolic;
		weight = newWeight;
		glucose = newGlucose;
	}
	
	//Getters - used in logDatabaseHelper
	public int getId() {
		return id;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public int getHr() {
		return hr;
	}
	
	public int getSystolic() {
		return systolic;
	}
	
	public int getDiastolic() {
		return diastolic;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getGlucose() {
		return glucose;
	}
	
}
