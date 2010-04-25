/**
 * 
 */
package ar.com.nybble.futbol.services.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrador
 *
 */


public class SpringContext {
	
	public static final ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
	
	

}
