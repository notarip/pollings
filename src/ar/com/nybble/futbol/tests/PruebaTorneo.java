package ar.com.nybble.futbol.tests;

import java.util.Iterator;

import ar.com.nybble.futbol.Partido;
import ar.com.nybble.futbol.Torneo;
import ar.com.nybble.futbol.common.ContextFactory;
import ar.com.nybble.futbol.services.AbmClubService;
import ar.com.nybble.futbol.services.AbmTorneoService;

public class PruebaTorneo {
	
	
	public static void main(String[] args) {

		AbmTorneoService abmTorneo = (AbmTorneoService) ContextFactory.getInstancia().getBean("AbmTorneoService");
		AbmClubService abmClub = (AbmClubService) ContextFactory.getInstancia().getBean("AbmClubService");

		
		
		//abmTorneo.crearTorneo("apertura 2010", abmClub.buscarClubs(),new Integer(2), TipoDeTorneo.LIGA_DOBLE, new Fecha(2010,1,1));
		Torneo torneo = abmTorneo.buscarTorneo(new Long(2));
		
	
//		System.out.println(torneo.getCantidadClubs());
//		System.out.println(torneo.getFechaDeCreacion());
//		System.out.println(torneo.getFechaDeHabilitacion());
		
//		for (Iterator iterator = torneo.getPartidos().iterator(); iterator.hasNext();) {
//			Partido partido = (Partido) iterator.next();
//			System.out.println(partido);
//			
//		}
//		
		
		
	
		
		
		
	}

}
