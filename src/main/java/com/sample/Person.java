package com.sample;

public class Person {
	
	private int age;
	private int durationOfLicense;
	
	private double value;
	private boolean safetyTraining;
	private boolean newCustomer;
	private boolean claim;
	
	
	public Person () {
		super();
		age = -1;
		durationOfLicense = -1;
		value = -1;
		safetyTraining = false;
		newCustomer = false;
		claim = false;
	}
	
	public Person(int age, int durationOfLicense, double value,
			boolean safetyTraining, boolean newCustomer, boolean claim) {
		super();
		this.age = age;
		this.durationOfLicense = durationOfLicense;
		this.value = value;
		this.safetyTraining = safetyTraining;
		this.newCustomer = newCustomer;
		this.claim = claim;
	}
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getDurationOfLicense() {
		return durationOfLicense;
	}
	public void setDurationOfLicense(int durationOfLicense) {
		this.durationOfLicense = durationOfLicense;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public boolean isSafetyTraining() {
		return safetyTraining;
	}
	public void setSafetyTraining(boolean safetyTraining) {
		this.safetyTraining = safetyTraining;
	}
	
	public boolean isNewCustomer() {
		return newCustomer;
	}

	public void setNewCustomer(boolean neukunde) {
		this.newCustomer = neukunde;
	}

	public boolean isClaim() {
		return claim;
	}

	public void setClaim(boolean claim) {
		this.claim = claim;
	}

	
	public void output () {
		System.out.println("--------- Person --------- ");
		if (age != -1) {
			System.out.println(" Alter: "+ age +" Jahre" );
		}
		if (durationOfLicense != -1) {
			System.out.println("Fuehrerscheinjahre: "+durationOfLicense);
		}
		if (value != -1) { 
			System.out.println("Umsatz: "+value+" Û");
		}
		if (safetyTraining) {
			System.out.println("Fahrsicherheitstraining: ja");
		} else {
			System.out.println("Fahrsicherheitstraining: nein");
		}
	}	
}
