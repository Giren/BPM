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
			
			Person driver1 = new Person(22, 1, 0, false, false, false);
			driver1.output();
			
			Person pa = new Person(50, 25, 0, true, true, true);
			pa.output();
			
			Calendar ca = Calendar.getInstance();
			ca.set(2016, Calendar.MARCH, 21);
			
			ArrayList<Person> drivers = new ArrayList<Person>();
			drivers.add(driver1);
			drivers.add(pa);
	
			request requestA = new request(CarModel.middleClass, ca, 10, carPool, drivers, false);
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
