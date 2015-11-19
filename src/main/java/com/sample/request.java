package com.sample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class request {
	private double specialDiscount = 25;
	private double maxDiscount = 100;
	
	private int numberOfFreeDays;
	private CarModel carModel;
	private int carPrice;
	private Date dateOfLoan;
	private Calendar calendar;
	private int numberOfSpecialDays;
	private int decreasing;
	private int loanPeriod;
	private boolean automatic;
	
	private Person tenant; //Mieter
	private ArrayList<Person> drivers;
	
	private double totalPrice;
	private double discount; //Rabatt
	
	public request() {
		super();
	}
	
	public request(int carPrice, Calendar calendar, int loanPeriod, CarPool carPool, ArrayList<Person> drivers, boolean automatic) {
		super();
		dateOfLoan = calendar.getTime();
		carModel = carPool.getCar(carPrice);
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
		
		this.tenant = lookForTenant();
	}
	
	public Person lookForTenant() {
		Person worstPerson = null;
		
		for(Person p:drivers){
			if(p.isSafetyTraining() == true){
				p.addDiscount(5);
			}
			
			if(p.isNewbie() == true){
				p.addDiscount(-10);
			}
		}
		
		for(Person p:drivers){
			if(worstPerson == null){
				worstPerson = p;
			}
			if(p.getDiscount() < worstPerson.getDiscount()){
				worstPerson = p;
			}
		}
		
		System.out.println("-> worstPerson:" + worstPerson.getAge() + " <-");
		return worstPerson;
	}
	
	public void calculateNumberOfFreeDays(){
		numberOfFreeDays = loanPeriod / 7;
		loanPeriod -= numberOfFreeDays;
	}
	
	//muss vor calculateNumberOfFreeDays aufgerufen werden
	public void calculateSpecialDays(){
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
		totalPrice = (loanPeriod - numberOfSpecialDays) * carModel.getPrice();
		totalPrice += numberOfSpecialDays * (carModel.getPrice() * ((100-specialDiscount)/100) );
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
		System.out.println("Wagenpreis: "+ carPrice +" Û/Tag");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		System.out.println("Leidatum: " + dateFormat.format(dateOfLoan));
		System.out.println("Leihdauer: "+ loanPeriod);
	}
	
	public void outputOffer () {
		System.out.println("--------- Angebot ---------");
		System.out.println("Wagenpreis: "+ carPrice +" Û/Tag");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		System.out.println("Leidatum: " +dateFormat.format(dateOfLoan));
		System.out.println("Leihdauer: "+ loanPeriod);
		System.out.println("Gesamtpreis: "+ totalPrice +" Û");
		System.out.println("Rabatt: "+ discount +"%");	
		System.out.println("Nachlass: "+ decreasing +"Û");
		System.out.println("Wochenende: "+ numberOfSpecialDays);
		System.out.println("Freie Tage: "+ numberOfFreeDays);
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
}
