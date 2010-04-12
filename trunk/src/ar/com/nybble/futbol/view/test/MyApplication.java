package ar.com.nybble.futbol.view.test;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * @author Administrador
 */
public class MyApplication extends WebApplication {
    public MyApplication() {
    	
        
    }

    public void init() {
    	
    
    }

	@Override
	public Class getHomePage() {
		// TODO Auto-generated method stub
		return AbmClub.class;
	}
}
  