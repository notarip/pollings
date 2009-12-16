/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author notarip
 *
 */
@Entity
public class CambioDeEstado {

	private TipoEstadosJugador estado;
	private Date fecha;
	private Jugador jugador;

	public CambioDeEstado(TipoEstadosJugador estado, Date fecha, Jugador jugador) {
		this.estado = estado;
		this.fecha = fecha;
		this.jugador = jugador;
	}

	
	public TipoEstadosJugador getEstado() {
		return this.estado;
	}

	@Temporal(value=TemporalType.DATE)
	@Id
	public Date getFecha() {
		return this.fecha;
	}

	@Id
	@ManyToOne
	@JoinColumn (name = "JUGADOR_ID", nullable = false)
	public Jugador getJugador() {
		return this.jugador;
	}

}
