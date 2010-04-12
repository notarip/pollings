/**
 * 
 */
package ar.com.nybble.futbol.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import ar.com.nybble.futbol.CambioDeEstado;
import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.TipoEstadosJugador;

/**
 * @author notarip
 *
 */
public class AltaCambioDeEstado {

	TipoEstadosJugador estado = TipoEstadosJugador.ACTIVO;
	Date fecha = new Date();
	Jugador jugador = new Jugador(new Documento("29317973",TipoDeDocumento.DNI));
	CambioDeEstado cambio = new CambioDeEstado(estado,fecha,jugador);
	/**
	 * Genero un alta y guarda el estado 
	 */
	@Test
	public void generoUnAltaGuardaElEstado() {
		TipoEstadosJugador estado2 = estado;
		assertTrue(estado2==cambio.getEstado());
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
