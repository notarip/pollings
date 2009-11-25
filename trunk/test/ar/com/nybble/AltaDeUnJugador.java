/**
 * 
 */
package ar.com.nybble;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author notarip
 *
 */

public class AltaDeUnJugador{
	
	
	
	/**
	 * Se crea en jugador con su nombre
	 */
	@Test
	public void agregaElNombreDelJugador() {
		Jugador jugador1 = new Jugador();
		jugador1.setNombre("Pablo Notari");
		assertTrue(jugador1.getNombre() == "Pablo Notari");
	}
	
	
	/**
	 * Se  
	 */
	@Test
	public void agregaFechaDeNacimientoDelJugador() {
		Jugador jugador1 = new Jugador();
		Fecha fecha1 = new Fecha("01/01/1980");
		jugador1.setNacimiento(fecha1);
		assertTrue(jugador1.getFecha() == fecha1); 
	}	

	@Test
	public void agregaClubFederado() {
		Jugador jugador1 = new Jugador();
		Club club1 = new Club();
		jugador1.agregarClub(club1);
		assertTrue(jugador1.getClubVigente() == club1);
	}
	
	@Test
	public void juagdorSinClub() {
		Jugador jugador1 = new Jugador();
		assertTrue(jugador1.getClubVigente() == null);
	}
	
	
	
}
