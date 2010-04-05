/**
 * 
 */
package ar.com.nybble.futbol;

/**
 * @author notarip
 *
 */
public class Partido {
	
	private Club local = null;
	private Club visita = null;
	private Integer fecha = null;

	public Partido(Club local, Club visita, int fecha) {
		this.local = local;
		this.visita = visita;
		this.fecha = fecha;
	}
	
	public Partido() {
		// TODO Auto-generated constructor stub
	}
	

	public Club getLocal() {
		return local;
	}
	
	public Club getVisita() {
		return visita;
	}
	
	public Integer getFecha() {
		return fecha;
	}

	@Override
	public String toString() {
		return local + " " + visita + " " + fecha;
		
	}


}
