/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.nybble.futbol.common.Fecha;

/**
 * @author notarip
 *
 */
@Entity
@Table (name = "Partido")
public class Partido {
	
	private long Id;
	private Club local = null;
	private Club visita = null;
	private Date fecha = null;
	private Torneo torneo = null;

	public Partido(Club local, Club visita, Fecha fecha, Torneo torneo) {
		this.local = local;
		this.visita = visita;
		this.fecha = fecha;
		this.torneo = torneo;
	}
	
	public Partido() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return Id;
	}
	
	
	@OneToOne
	public Club getLocal() {
		return local;
	}
	
	@OneToOne
	public Club getVisita() {
		return visita;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getFecha() {
		return fecha;
	}
	
	@ManyToOne
	@JoinColumn (name = "TORNEO_ID")
	public Torneo getTorneo() {
		return torneo;
	}

	public void setLocal(Club local) {
		this.local = local;
	}
	
	public void setVisita(Club visita) {
		this.visita = visita;
	}
	
	private void setId(long id) {
		Id = id;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	private void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	
	
	@Override
	public String toString() {
		return local + " " + visita + " " + fecha;
		
	}


}
