package com.sample;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class carRental {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-dtables");
           
        	CarPool carPool = new CarPool();
        	
			// Test a
			System.out.println("Test a:");
			
			Person driver1 = new Person();
			driver1.setDurationOfLicense(20);
			driver1.setAge(25);
			driver1.output();
			
			Person pa = new Person();
			pa.setDurationOfLicense(20);
			pa.setAge(50);
			pa.setSafetyTraining(true);
			pa.setNewCustomer(true);
			pa.setClaim(true);
			pa.output();
			
			Calendar ca = Calendar.getInstance();
			ca.set(2016, Calendar.MARCH, 25);
			
			ArrayList<Person> drivers = new ArrayList<Person>();
			//drivers.add(driver1);
			drivers.add(pa);
	
			request requestA = new request(CarModel.middleClass, ca, 10, carPool, drivers);
			requestA.setAutomatic(true);
			requestA.output();

			kSession.insert(requestA);
			kSession.fireAllRules();
			
			requestA.outputOffer();
			
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
