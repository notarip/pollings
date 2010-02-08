package ar.com.nybble.futbol.tests;

import org.springframework.context.ApplicationContext;

import ar.com.nybble.futbol.Jugador;
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
		
		Jugador pablo = abmJugador.buscarJugador(new Long(13));
		System.out.println(pablo);
		
		System.out.println("listo");
		

	}

}
