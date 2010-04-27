package ar.com.nybble.futbol.tests;



import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.common.ContextFactory;
import ar.com.nybble.futbol.services.AbmClubService;


public class ManejoAbmClub {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		
		AbmClubService abmClub = (AbmClubService) ContextFactory.getInstancia().getBean("AbmClubService");
		
		
		
		abmClub.crearClub("RACING");
		
//		for (Iterator iterator = abmClub.buscarClubsPorNombre("boca"); iterator.hasNext();) {
//			Club club = (Club) iterator.next();
//			System.out.println(club);
//		}
		 
				
		System.out.println("listo !");
		
//		Club boca = abmClub.buscarClub(new Long(1));
//		System.out.println(boca);
//		boca.setNombre("BOCA JUNIORS!!");
//		abmClub.modificarClub(boca);
//		boca = abmClub.buscarClub(new Long(1));
//		System.out.println(boca);

	}

}
