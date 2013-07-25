package com.logisome.metasome;

public class SymptomPanel {
	
	private int id = 0;
	private long timestamp = 0;
	private int sob = 0;
	private int edema = 0;
	private int cough = 0;
	private int speed = 0;
	private int pain = 0;
	private int fatigue = 0;
	private int inhaler = 0;
	
	public SymptomPanel(long newTimestamp, int newSob, int newEdema, int newCough, int newSpeed, int newPain, 
			int newFatigue, int newInhaler) {

		timestamp = System.currentTimeMillis() / 1000L;
		setAll(newTimestamp, newSob, newEdema, newCough, newSpeed, newPain, newFatigue, newInhaler);
		
	}
	
	public void setAll(long newTimestamp, int newSob, int newEdema, int newCough, int newSpeed, int newPain, 
		int newFatigue, int newInhaler) {
		
		sob = newSob;
		edema = newEdema;
		cough = newCough;
		speed = newSpeed;
		pain = newPain;
		fatigue = newFatigue;
		inhaler = newInhaler;
		
	}

	public int getId() {
		return id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getSob() {
		return sob;
	}

	public int getEdema() {
		return edema;
	}

	public int getCough() {
		return cough;
	}

	public int getSpeed() {
		return speed;
	}

	public int getPain() {
		return pain;
	}

	public int getFatigue() {
		return fatigue;
	}

	public int getInhaler() {
		return inhaler;
	}
}
