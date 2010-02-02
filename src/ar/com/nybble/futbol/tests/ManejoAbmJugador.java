package ar.com.nybble.futbol.tests;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Nacionalidad;
import ar.com.nybble.futbol.services.AbmJugadorService;
import ar.com.nybble.futbol.test.TipoDeDocumento;

public class ManejoAbmJugador {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
		AbmJugadorService abmJugador = (AbmJugadorService) context.getBean("AbmJugadorService");
		
		
		abmJugador.crearJugador("PABLO NOTARI", new Date(), new Nacionalidad("ARGENTINO"), new Documento("29317973", TipoDeDocumento.DNI));
		
		System.out.println("listo");
		

	}

}
