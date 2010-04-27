package ar.com.nybble.futbol.tests;

import java.util.Date;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.Nacionalidad;
import ar.com.nybble.futbol.common.ContextFactory;
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


		
/*		
		Nacionalidad nac = new Nacionalidad("ARGENTINA");
		Documento doc = new Documento("14300001", TipoDeDocumento.DNI);
		Date fecha = new Date();
		abmJugador.crearJugador("Diego Maradona", fecha, nac, doc);
	*/
		Club club = abmClub.buscarClub(new Long(4));
		
		Jugador pablo = abmJugador.buscarJugador(new Long(8));
		pablo.agregarClub(club, new Date());
		abmJugador.modificarJugador(pablo);

		
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
