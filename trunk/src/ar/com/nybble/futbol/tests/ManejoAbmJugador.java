package ar.com.nybble.futbol.tests;



import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;

import ar.com.nybble.futbol.CambioDeEstado;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.TipoDeLesion;
import ar.com.nybble.futbol.common.ContextFactory;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.services.AbmJugadorService;

public class ManejoAbmJugador {

	/**
	 * @param args
	 * @throws DataSourceException 
	 */
	public static void main(String[] args) throws DataSourceException {
		
		ApplicationContext context = ContextFactory.getInstancia();
		AbmJugadorService abmJugador = (AbmJugadorService) context.getBean("AbmJugadorService");
				

//		Nacionalidad nac = new Nacionalidad("ARGENTINO");
//		Documento doc = new Documento("29317973", TipoDeDocumento.DNI);
//		Date fecha = new Date();
//		abmJugador.crearJugador("PABLO NOTARI", fecha, nac, doc);
		
		Jugador pablo = abmJugador.buscarJugador(new Long(5));
//		List lista = pablo.getEstados();
//		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
//			CambioDeEstado object = (CambioDeEstado) iterator.next();
//			System.out.println(object.getEstado().toString());	
//		}
//		
//		System.out.println(pablo.getEstado().ordinal());
//		System.out.println(pablo.getEstado());
		
		//pablo.notificarLesion(new Date(), TipoDeLesion.FRACTURA_PERONE);
		pablo.recuperarActividad(new Date());
		
		abmJugador.modificarJugador(pablo);
		System.out.println(pablo);
		
		System.out.println("listo") ;
		

	}

}
