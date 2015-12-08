package com.sample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyRequest {

	private double specialDiscount = 25;
	private double maxDiscount = 100;
	
	private double downgradeDiscount;
	private int numberOfFreeDays;
	private CarModel carModel;
	private int carPrice;
	private Date dateOfLoan;
	private Calendar calendar;
	private int numberOfSpecialDays;
	private int decreasing;
	private int loanPeriod;
	private boolean automatic;
	private boolean save;
	private boolean newbie;
	private Person tenant; //Mieter
	private ArrayList<Person> drivers;
	private double totalPrice;
	private double discount; //Rabatt
	private boolean upgrade;
	private boolean downgrade;
	
	public MyRequest() {
		super();
	}
	
	public MyRequest(Person tenant, int carPrice, Calendar calendar, int loanPeriod, CarPool carPool, ArrayList<Person> drivers, boolean automatic) {
		super();
		
		dateOfLoan = calendar.getTime();
		this.downgradeDiscount = 0;
		this.loanPeriod = loanPeriod;
		this.calendar = calendar;
		this.carPrice = carPrice;
		this.drivers = drivers;
		this.totalPrice = 0;
		this.discount = 0;
		this.decreasing = 0;
		this.numberOfFreeDays = 0;
		this.numberOfSpecialDays = 0;
		this.automatic = automatic;
		this.tenant = tenant;
		
		carModel = carPool.getCar(carPrice);
		if(carModel != null)
			checkCarModel(carPrice);
		
		this.save = hasEverybodySafetyTraining();
		this.newbie = isSomeoneANewbie();
	}
	
	public void checkCarModel(int originalCarPrice){
		int newPrice = carModel.getPrice();
		if(newPrice != originalCarPrice){
			if(newPrice > originalCarPrice){
				upgrade = true;
			} else {
				downgrade = true;
				downgradeDiscount = 10;
				this.setCarPrice(newPrice);
			}
		} else{
			upgrade = false;
			downgrade = false;
		}
	}
	
	public boolean isSomeoneANewbie(){
		for(Person p:drivers){
			if(p.isNewbie() == true){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasEverybodySafetyTraining(){
		for(Person p:drivers){
			if(p.isSafetyTraining() == false){
				return false;
			}
		}
		return true;
	}
	
	public void calculateNumberOfFreeDays(){
		numberOfFreeDays = loanPeriod / 7;
		loanPeriod -= numberOfFreeDays;
	}
	
	//muss vor calculateNumberOfFreeDays aufgerufen werden
	public void calculateSpecialDays(){ //Wochenende bzw Feiertage
		//TODO Feiertage 
		Calendar ca = Calendar.getInstance();
		for(int i=0;i<loanPeriod;i++){
			if(i%7 > 0 || i == 0) {
				ca.set(2016, Calendar.MARCH, dateOfLoan.getDate()+i);
				if(ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			        numberOfSpecialDays++;
			}
		}
	}
	
	public void addDiscount(double addDiscount) {
		this.discount += addDiscount;
	}
	
	public void addDecreasing(int decreasing){
		this.decreasing += decreasing;
	}
	
	public void addDriver (Person newDriver) {
		this.drivers.add(newDriver);
	}
	
	public void calculateOfferPrice () {
		totalPrice = (loanPeriod - numberOfSpecialDays) * carPrice;
		//Rabatte die nicht zur Regel 9 (Limit durch Rabat bei 100) reinzählen
		totalPrice += numberOfSpecialDays * (carPrice * ((100-(specialDiscount))/100) );
		totalPrice = totalPrice * (100-downgradeDiscount) / 100;
		totalPrice -= decreasing;
		
		if(totalPrice * (discount/100) > maxDiscount){
			totalPrice -= maxDiscount;
			System.out.println("LIMIT erreicht !!!!!!");
		} else{
			totalPrice -= totalPrice * (discount/100);
			System.out.println("LIMIT NICHT erreicht !!!!!!");
		}
	}
	
	public void output () {
		System.out.println("--------- Anfrage --------- ");
		System.out.println("Wagenpreis: "+ carPrice +" €/Tag");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		System.out.println("Leidatum: " + dateFormat.format(dateOfLoan));
		System.out.println("Leihdauer: "+ loanPeriod);
		System.out.println("Anzahl Fahrer: "+ drivers.size());
		if (automatic) {
			System.out.println("Automatic: ja");
		} else {
			System.out.println("Automatic: nein");
		}
	}
	
	public String outputOffer () {
		String offer = new String();
		offer += "--------- Angebot --------- \n";
		
		switch(carModel.getPrice()){
			case CarModel.smallClass:
				offer += "Model: Kleinwagen\n";
				break;
			case CarModel.compactClass:
				offer += "Model: Kompaktwagen\n";
				break;
			case CarModel.middleClass:
				offer += "Model: Mittelklassewagen\n";
				break;
			case CarModel.upperClass:
				offer += "Model: Oberklassewagen\n";
				break;
		}
		
		offer += "Wagenpreis: "+ carPrice +" €/Tag\n";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		offer += "Leidatum: " + dateFormat.format(dateOfLoan) + "\n";
		offer += "Leidauer: " + loanPeriod + "\n"; 
		offer += "Gesamtpreis: " + totalPrice +" €\n";
		offer += "Rabatt: " + discount +"%\n";
		offer += "Nachlass: " + decreasing +"€\n";
		offer += "Wochenende: " + numberOfSpecialDays + "\n";
		offer += "Freie Tage: " + numberOfFreeDays + "\n";
		if (downgrade) {
			offer += "Rabatt für Downgrade: " + downgradeDiscount + "%\n";
		} 
		if (save) {
			offer += "Bonus Fahrsicherheitstraining: ja\n";
		} else {
			offer += "Bonus Fahrsicherheitstraining: nein\n";
		}
		if (newbie) {
			offer += "Aufpreis Fahranfänger: ja\n";
		} else {
			offer += "Aufpreis Fahranfänger: nein\n";
		}
		if (automatic) {
			offer += "Aufpreis Automatik: ja\n";
		} else {
			offer += "Aufpreis Automatik: nein\n";
		}
		if (upgrade) {
			offer += "kostenloses Upgrade: ja\n";
		} else {
			offer += "kostenloses Upgrade: nein\n";
		}
		
		return offer;
	}
	
	

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(int carPrice) {
		this.carPrice = carPrice;
	}

	public Date getDateOfLoan() {
		return dateOfLoan;
	}

	public void setDateOfLoan(Date dateOfLoan) {
		this.dateOfLoan = dateOfLoan;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public boolean isAutomatic() {
		return automatic;
	}

	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}

	public Person getTenant() {
		return tenant;
	}

	public void setTenant(Person tenant) {
		this.tenant = tenant;
	}

	public ArrayList<Person> getDrivers() {
		return drivers;
	}

	public void setDrivers(ArrayList<Person> drivers) {
		this.drivers = drivers;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public int getDecreasing() {
		return decreasing;
	}

	public void setDecreasing(int decreasing) {
		this.decreasing = decreasing;
	}

	public int getNumberOfFreeDays() {
		return numberOfFreeDays;
	}

	public void setNumberOfFreeDays(int numberOfFreeDays) {
		this.numberOfFreeDays = numberOfFreeDays;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	public boolean isNewbie() {
		return newbie;
	}

	public void setNewbie(boolean newbie) {
		this.newbie = newbie;
	}
	
	public boolean isUpgrade(){
		return this.upgrade;
	}
}
