/**
 * 
 */
package ar.com.nybble.futbol.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ar.com.nybble.futbol.CambioDeClub;
import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;

/**
 * @author notarip
 *
 */
public class AltaCambioDeClub {
	
	Club club = new Club("Milan");
	Date fecha = new Date();
	Jugador jugador = new Jugador(new Documento("29317973",TipoDeDocumento.DNI));
	CambioDeClub cambio = new CambioDeClub(club,fecha,jugador);
	/**
	 * Genero un alta y guarda el club 
	 */
	@Test
	public void generoUnAltaGuardaElClub() {
		Club club2 = club;
		assertEquals(club2,cambio.getClub());
	}
	

	/**
	 * Genero un alta y guarda la fecha
	 */
	@Test
	public void generoUnAltaGuardaLaFecha() {
		Date fecha2 = fecha;
		assertEquals(fecha2, cambio.getFecha());
	}
	
	/**
	 * Se lo asocio a un jugador y lo guarda
	 */
	@Test
	public void seLoAsocioUnJugadorLoGuarda() {
		Jugador jugador2 = jugador;
		assertEquals(jugador2, cambio.getJugador());

	}
	
	
	




}
