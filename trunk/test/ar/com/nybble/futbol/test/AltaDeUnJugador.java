/**
 * 
 */
package ar.com.nybble.futbol.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;


/**
 * @author notarip
 *
 */
public class AltaDeUnJugador{
	
		Jugador jugador1 = new Jugador();
		Date fecha1 = new Date();
		Nacionalidad nigeriano = new Nacionalidad("Nigeriano");
		

	
	/**
	 * Se crea en jugador con su nombre
	 */
	@Test
	public void agregaElNombreDelJugador() {
		jugador1.setNombre("Pablo Notari");
		assertTrue(jugador1.getNombre() == "Pablo Notari");
	}
	
	
	/**
	 * Se setea la fecha de nacimiento de un Jugador 
	 */
	@Test
	public void agregaFechaDeNacimientoDelJugador() {
		jugador1.setNacimiento(fecha1);
		assertTrue(jugador1.getFecha() == fecha1); 
	}	

	/**
	 * Agregar una Nacionalidad al Jugador
	 */
	@Test
	public void agregarNacionalidadJugador() {
		jugador1.setNacionalidad(nigeriano);
		assertEquals(jugador1.getNacionalidad(),nigeriano);
	}
  
	
	
}
