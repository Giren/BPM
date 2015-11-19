package com.sample;

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
			Person pa = new Person();
			pa.setDurationOfLicense(20);
			pa.setValue(3000);
			pa.setSafetyTraining(true);
			pa.setNewCustomer(true);
			pa.output();
			
			Calendar ca = Calendar.getInstance();
			ca.set(2015, 10-1, 1);
			Date da = ca.getTime();
			request requestA = new request(CarModel.middleClass, da, 5, pa, carPool);
			requestA.output();

			kSession.insert(requestA);
			kSession.fireAllRules();
			
			requestA.outputOffer();
			
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class Message {

        public static final int REQUEST = 0;
        public static final int GOODBYE = 1;
        public static final int END = 2;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
