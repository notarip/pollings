package ar.com.nybble.futbol.tests;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.Nacionalidad;
import ar.com.nybble.futbol.common.ContextFactory;
import ar.com.nybble.futbol.common.Fecha;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.services.AbmClubService;
import ar.com.nybble.futbol.services.AbmJugadorService;
import ar.com.nybble.futbol.test.TipoDeDocumento;

/**
 * @author notarip
 *
 */
public class ManejoAbmJugador {

	/**
	 * @param args
	 * @throws DataSourceException 
	 */

	public static void main(String[] args) throws DataSourceException {
		
		ApplicationContext context = ContextFactory.getInstancia();
		AbmJugadorService abmJugador = (AbmJugadorService) context.getBean("AbmJugadorService");
		AbmClubService abmClub = (AbmClubService) context.getBean("AbmClubService");


		Long dni = new Long(10000009); //IMPORTANTE: DESPUES DE UN CORRIDA ACTUALIZAR !!!!
		List nombres = new LinkedList<String>();
//		nombres.add("DIEGO MARADONA");
//		nombres.add("PABLO NOTARI");
//		nombres.add("SERGIO SATURNO");
//		nombres.add("CARLOS MAC ALLISTER");
//		nombres.add("VICTOR MAGNANO");
//		nombres.add("FELIPE MAGNELLI");
//		nombres.add("RAUL MARADONA");
//		nombres.add("NELSON VIVAS");
		nombres.add("Jorge da Silva");
		nombres.add("Hugo De León");
		nombres.add("Carlos Diogo");
		nombres.add("Carlos Enrique");
		nombres.add("Juan Joya");
		nombres.add("Omar Enrique Mallea");
		nombres.add("Reinaldo Merlo");
		nombres.add("Daniel Montenegro");		
/*
		
		
		for (Iterator iterator = nombres.iterator(); iterator.hasNext();) {
			String nombre = (String) iterator.next();
			dni++;
			Documento doc = new Documento(dni.toString(), TipoDeDocumento.DNI);
			abmJugador.crearJugador(nombre, new Fecha(), new Nacionalidad("ARGENTINA"), doc);
		}
	

		Club club = abmClub.buscarClub(new Long(1));
		for (Iterator iterator = nombres.iterator(); iterator.hasNext();) {
			String nombre = (String) iterator.next();
			Jugador pablo = (Jugador) abmJugador.buscarJugadoresPorNombre(nombre).next();
			pablo.agregarClub(club, new Fecha());
			abmJugador.modificarJugador(pablo);
		}
*/		

		
//		Iterator<Jugador> jugadores = abmJugador.buscarJugadoresPorClub(new Long(1));
//		for (Iterator<Jugador> iterator = jugadores; iterator.hasNext();) {
//			Jugador object = (Jugador) iterator.next();
//			System.out.println(object);
//			System.out.println(object.getClubVigente());
//		}
		
//		Iterator<Jugador> jugadores =  abmJugador.buscarJugadores();
//		for (Iterator iterator = jugadores; iterator.hasNext();) {
//			Jugador type = (Jugador) iterator.next();
//			System.out.println(type);
//			
//		}
		
		
		//pablo.agregarClub(club, new Date());

		
//		List lista = pablo.getEstados();
//		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
//			CambioDeEstado object = (CambioDeEstado) iterator.next();
//			System.out.println(object.getEstado().toString());	
//		}
//		
//		System.out.println(pablo.getEstado().ordinal());
//		System.out.println(pablo.getEstado());
		
//		pablo.notificarLesion(new Date(), TipoDeLesion.FRACTURA_PERONE);
		//pablo.recuperarActividad(new Date());
		
		//abmJugador.modificarJugador(pablo);
		//System.out.println(pablo);
		
		System.out.println("listo") ;
		

	}

}
