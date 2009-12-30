package ar.com.nybble.futbol.tests;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.services.AbmClubServiceFactory;


public class ManejoAbmClub {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
		
		System.out.println("listo !");
		//AbmClubServiceFactory.getAbmclub().crearClub("RIVER PLATE");
//		Club boca = AbmClubServiceFactory.getAbmclub().buscarClub(new Long(5));
//		boca.setNombre("BOCA JUNIORS");
//		AbmClubServiceFactory.getAbmclub().modificarClub(boca);

	}

}
