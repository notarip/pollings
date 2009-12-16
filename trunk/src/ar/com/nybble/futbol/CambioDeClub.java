/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.Date;

/**
 * @author notarip
 *
 */

public class CambioDeClub {

	private Date fecha;
	private Club club;
	private Jugador jugador;
	

	public CambioDeClub(Club club, Date fecha, Jugador jugador) {
		this.fecha = fecha;
		this.club = club;
		this.jugador =(jugador);
	}

	public Club getClub() {
		return this.club;
	}

	public Object getFecha() {
		return this.fecha;
	}


	public Jugador getJugador() {
		return jugador;
	}
	
	

}
