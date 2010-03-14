/**
 * 
 */
package ar.com.nybble.futbol;


import java.util.Comparator;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class CambioDeEstado implements Comparable<CambioDeEstado>{

	/**
	 * 
	 */
	
	private long Id;
	private TipoEstadosJugador estado;
	private Date fecha;
	private Jugador jugador;

	
	
	public CambioDeEstado() {
		// TODO Auto-generated constructor stub
	}

	public CambioDeEstado(TipoEstadosJugador estado, Date fecha, Jugador jugador) {
		this.estado = estado;
		this.fecha = fecha;
		this.jugador = jugador;
	}

	
	public void setId(long id) {
		Id = id;
	}
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public long getId() {
		return Id;
	}

	public TipoEstadosJugador getEstado() {
		return this.estado;
	}

	@Temporal(value=TemporalType.TIMESTAMP)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha){
		this.fecha = fecha;
		
	}
	
	public void setEstado(TipoEstadosJugador estado){
		this.estado = estado;
	}
	
	
	@ManyToOne
	@JoinColumn (name = "JUGADOR_ID", nullable = false)
	public Jugador getJugador() {
		return this.jugador;
	}
	
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}

	@Override
	public int compareTo(CambioDeEstado o) {
		return	this.fecha.compareTo(o.getFecha());
	}

	
	@Override
	public boolean equals(Object obj) {
		CambioDeEstado otro = (CambioDeEstado) obj;
		return (this.getEstado() == otro.getEstado() && (this.hashCode() == otro.hashCode()));
			
	}
	

		

}
