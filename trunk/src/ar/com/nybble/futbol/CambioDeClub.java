/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author notarip
 *
 */

@Entity
public class CambioDeClub implements Comparable<CambioDeClub>{

	private Date fecha;
	private Club club;
	private Jugador jugador;
	

	public CambioDeClub() {
		// TODO Auto-generated constructor stub
	}
	
	public CambioDeClub(Club club, Date fecha, Jugador jugador) {
		this.fecha = fecha;
		this.club = club;
		this.jugador =(jugador);
	}

	@ManyToOne
	@JoinColumn (name = "CLUB_ID")
	public Club getClub() {
		return this.club;
	}

	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
	
	public void setClub(Club club){
		this.club =club;
	}
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Id
	public Date getFecha() {
		return this.fecha;
	}

	
	
	@ManyToOne
	@JoinColumn (name = "JUGADOR_ID", nullable = false)
	public Jugador getJugador() {
		return jugador;
	}
	
	
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}

	@Override
	public int compareTo(CambioDeClub o) {
		return this.fecha.compareTo(o.fecha);
		
	}
	

}
