package com.sample;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class CarRental {

	private static CarPool carPool;
	private static MyRequest req;
	private static AuthGui authGui;
	
    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	final KieSession kSession = kContainer.newKieSession("ksession-dtables");
          
        	carPool = new CarPool();
        	
			final GuiRental gui = new GuiRental();
			gui.show(true);
			gui.addWindowListener(new WindowListener() {
				@Override
				public void windowClosed(WindowEvent e) {
					req = gui.getRequest();
					
					if(req.getCarModel() != null){
						req.output();
						kSession.insert(req);
						kSession.fireAllRules();					
						offerOutput();
					} else {
						new GuiOffer("keine Kapazitäten für gewünschtes Modell vorhanden").show(true);
					}

					gui.setVisible(true);
				}
				
				@Override
				public void windowDeactivated(WindowEvent e) {
					
				}
				
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
				}
			});	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public static void offerOutput(){
    	if(proofOffer()){
    		authGui = new AuthGui();
    		authGui.show(true);
    		authGui.addWindowListener(new WindowListener() {
    			@Override
    			public void windowClosed(WindowEvent e) {
    				if(authGui.isAuth() == false){
    					new GuiOffer("abgelehnt").show(true);
    					carPool.addCarModel(req.getCarModel().getPrice());
    				} else{
    					new GuiOffer(req.outputOffer()).show(true);
    				}
    			}
    	
    			@Override
    			public void windowActivated(WindowEvent arg0) {
    				// TODO Auto-generated method stub
    				
    			}
    	
    			@Override
    			public void windowClosing(WindowEvent arg0) {
    				// TODO Auto-generated method stub
    				
    			}
    	
    			@Override
    			public void windowDeactivated(WindowEvent arg0) {
    				// TODO Auto-generated method stub
    				
    			}
    	
    			@Override
    			public void windowDeiconified(WindowEvent arg0) {
    				// TODO Auto-generated method stub
    				
    			}
    	
    			@Override
    			public void windowIconified(WindowEvent arg0) {
    				// TODO Auto-generated method stub
    				
    			}
    	
    			@Override
    			public void windowOpened(WindowEvent arg0) {
    				// TODO Auto-generated method stub
    				
    			}
    		}); 
    	} else {
    		new GuiOffer(req.outputOffer()).show(true);
    	}
    }
    
    //wenn true muss Leiter Angebot freigeben
    public static boolean proofOffer(){
    	if(req.isNewbie() && (req.getCarModel().getPrice() != CarModel.smallClass) ){
    		return true;
    	} else if(req.isUpgrade()){
    		return true;
    	} else{
    		return false;
    	}
    }
    
    public static CarPool getCarPool(){
    	return carPool;
    }
}
