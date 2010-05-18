package ar.com.nybble.futbol.tests;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer.AmbiguousBindingException;

import ar.com.nybble.futbol.Partido;
import ar.com.nybble.futbol.TipoDeTorneo;
import ar.com.nybble.futbol.Torneo;
import ar.com.nybble.futbol.common.ContextFactory;
import ar.com.nybble.futbol.common.Fecha;
import ar.com.nybble.futbol.services.AbmClubService;
import ar.com.nybble.futbol.services.AbmTorneoService;

public class PruebaTorneo {
	
	
	
	
	public static void main(String[] args) {
		AbmTorneoService abmTorneo = (AbmTorneoService) ContextFactory.getInstancia().getBean("AbmTorneoService");
		AbmClubService abmClub = (AbmClubService) ContextFactory.getInstancia().getBean("AbmClubService");
		System.out.println(abmTorneo);
		//abmTorneo.crearTorneo("apertura 2010", abmClub.buscarClubs(),new Integer(2), TipoDeTorneo.LIGA_DOBLE, new Fecha(2010,1,1));
		Torneo torneo = abmTorneo.buscarTorneos().next();
		System.out.println(torneo.getCantidadClubs());
		System.out.println(torneo.getFechaDeCreacion());
		System.out.println(torneo.getFechaDeHabilitacion());
		
		for (Iterator iterator = torneo.getPartidos().iterator(); iterator.hasNext();) {
			Partido partido = (Partido) iterator.next();
			System.out.println(partido);
			
		}
		
		
		
	
		
		
		
	}

}
