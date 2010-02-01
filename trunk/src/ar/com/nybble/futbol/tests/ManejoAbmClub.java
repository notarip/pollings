package ar.com.nybble.futbol.tests;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.services.AbmClubService;
import ar.com.nybble.futbol.services.AbmClubServiceFactory;


public class ManejoAbmClub {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
		AbmClubService abmClub = (AbmClubService) context.getBean("AbmClubService");
		
		
		System.out.println("listo !");
		//AbmClubServiceFactory.getAbmclub().crearClub("RIVER PLATE");
		Club boca = abmClub.buscarClub(new Long(5));
		System.out.println(boca);
//		AbmClubServiceFactory.getAbmclub().modificarClub(boca);

	}

}
