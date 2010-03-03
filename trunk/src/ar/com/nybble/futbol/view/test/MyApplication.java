package ar.com.nybble.futbol.view.test;

import org.apache.wicket.protocol.http.WebApplication;

import ar.com.nybble.futbol.view.Pagina1;

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
		return Pagina1.class;
	}
}
  