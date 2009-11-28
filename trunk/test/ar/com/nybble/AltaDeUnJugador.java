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
	
		Jugador jugador1 = new Jugador();
		Club club1 = new Club();

	
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
		Fecha fecha1 = new Fecha("01/01/1980");
		jugador1.setNacimiento(fecha1);
		assertTrue(jugador1.getFecha() == fecha1); 
	}	

	
	/**
	 * Se agrega un club aljugador y queda como vigente
	 */
	@Test
	public void agregaClubFederado() {
		jugador1.agregarClub(club1);
		assertTrue(jugador1.getClubVigente() == club1);
	}
	
	
	/**
	 * Si un jugador no tiene asignado club
	 */
	@Test
	public void juagdorSinClub() {
		assertTrue(jugador1.getClubVigente() == null);
	}
	

	
}
