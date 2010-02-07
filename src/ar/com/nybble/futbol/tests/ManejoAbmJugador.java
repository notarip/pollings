package ar.com.nybble.futbol.tests;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Nacionalidad;
import ar.com.nybble.futbol.dataSource.repositorio.NacionalidadRepositorio;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.services.AbmJugadorService;
import ar.com.nybble.futbol.test.TipoDeDocumento;

public class ManejoAbmJugador {

	/**
	 * @param args
	 * @throws DataSourceException 
	 */
	public static void main(String[] args) throws DataSourceException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
		AbmJugadorService abmJugador = (AbmJugadorService) context.getBean("AbmJugadorService");
		NacionalidadRepositorio nacion = (NacionalidadRepositorio) context.getBean("nacionalidadRepositorio");
		
//		try {
//			nacion.create(new Nacionalidad(("ARGENTINO")));
//		} catch (DataSourceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Nacionalidad nac = (Nacionalidad) nacion.findById(new Long(2));
		abmJugador.crearJugador("PABLO NOTARI", new Date(), nac, new Documento("29317973", TipoDeDocumento.DNI));
		
		System.out.println("listo");
		

	}

}
