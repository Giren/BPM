package com.sample;

public class Person {
	
	private int age;
	private int durationOfLicense;
	private int discount;
	
	private double value;
	private boolean safetyTraining;
	private boolean newCustomer;
	private boolean claim;
	private boolean newbie;
	
	
	public Person () {
		super();
		age = -1;
		durationOfLicense = 0;
		value = 0;
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
		this.setDiscount(0);
	}
	
	public void output () {
		System.out.println("--------- Person --------- ");
		if (age != 0) {
			System.out.println("Alter: "+ age +" Jahre" );
		}
		if (durationOfLicense != 0) {
			System.out.println("Fuehrerscheinjahre: "+durationOfLicense);
		}
		if (value != 0) { 
			System.out.println("Umsatz: "+value+" Û");
		}
		if (safetyTraining) {
			System.out.println("Fahrsicherheitstraining: ja");
		} else {
			System.out.println("Fahrsicherheitstraining: nein");
		}
		if (newCustomer) {
			System.out.println("Neukunde: ja");
		} else {
			System.out.println("Neukunde: nein");
		}
		if (claim) {
			System.out.println("Anspruch: ja");
		} else {
			System.out.println("Anspruch: nein");
		}
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public void addDiscount(int discount){
		this.discount += discount;
	}

	public boolean isNewbie() {
		return newbie;
	}

	public void setNewbie(boolean newbie) {
		this.newbie = newbie;
	}	
}
