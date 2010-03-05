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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
		AbmClubService abmClub = (AbmClubService) context.getBean("AbmClubService");
		
		
		
		abmClub.crearClub("BOCA JUNIORS");
		System.out.println("listo !");
//		Club boca = abmClub.buscarClub(new Long(1));
//		System.out.println(boca);
//		boca.setNombre("BOCA JUNIORS!!");
//		abmClub.modificarClub(boca);
//		boca = abmClub.buscarClub(new Long(1));
//		System.out.println(boca);

	}

}
