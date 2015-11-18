package com.sample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class request {
	private CarModel carModel;
	private int carPrice;
	private Date dateOfLoan;
	private int loanPeriod;
	private boolean automatic;
	
	private Person tenant; //Mieter
	private ArrayList<Person> drivers;
	
	private double totalPrice;
	private double discount; //Rabatt
	
	public request() {
		super();
		setDrivers(new ArrayList <Person>());
		setTotalPrice(-1);
		setDiscount(0);	
	}
	
	public request(int carPrice, Date dateOfLoan, int loanPeriod,
			Person tenant, CarPool carPool) {
		super();
		this.setDateOfLoan(dateOfLoan);
		this.setLoanPeriod(loanPeriod);
		this.setTenant(tenant);
		this.carModel = carPool.getCar(carPrice);
		setCarPrice(carPrice);
		setDrivers(new ArrayList<Person>());
		setTotalPrice(-1);
		setDiscount(0);
		
	}
	
	public void addDiscount(double addDiscount) {
		this.discount += addDiscount;
	}

	
	public void addDriver (Person newDriver) {
		this.drivers.add(newDriver);
	}
	
	public boolean alleHabenFahrsicherheitstraining () {
		boolean save = true;
		if (!tenant.isSafetyTraining())
			save = false;
		for (Person p: drivers) {
			if (!p.isSafetyTraining())
				save = false;
		}	
		return save;
	}
	
	public void calculatePreis () {
		totalPrice = totalPrice - totalPrice * (discount/100);
	}
	
	public void output () {
		System.out.println("--------- Anfrage --------- ");
		System.out.println("Wagenpreis: "+ carPrice +" Û/Tag");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		System.out.println("Leidatum: " + dateFormat.format(dateOfLoan));
		System.out.println("Leihdauer: "+ loanPeriod);
		if (totalPrice != -1)
			System.out.println("Gesamtpreis: "+ totalPrice +" Û");
		System.out.println("Rabatt: "+ discount +"%");	
	}
	
	public void outputRequest () {
		System.out.println("Angebot - ");
		System.out.println("Wagenpreis: "+ carPrice +" Û/Tag");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		System.out.println("Leidatum: " +dateFormat.format(dateOfLoan));
		System.out.println("Leihdauer: "+ loanPeriod);
		System.out.println("Gesamtpreis: "+ totalPrice +" Û");
		System.out.println("Rabatt: "+ discount +"%");	
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
}
