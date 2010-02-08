package ar.com.nybble.futbol.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextFactory {
	
	static ApplicationContext instancia;
	
	
	private ContextFactory() {
		
	}
	
	public static final ApplicationContext getInstancia(){
		if (instancia == null)
			instancia = new  ClassPathXmlApplicationContext("services.xml");
		 
		return instancia;
		
		
	}

}
