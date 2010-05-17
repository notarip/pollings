/**
 * 
 */
package ar.com.nybble.futbol;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Fecha fecha = null;

	public Partido(Club local, Club visita, Fecha fecha) {
		this.local = local;
		this.visita = visita;
		this.fecha = fecha;
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
	public Fecha getFecha() {
		return fecha;
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
	
	@Override
	public String toString() {
		return local + " " + visita + " " + fecha;
		
	}


}
