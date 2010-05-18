/**
 * 
 */
package ar.com.nybble.futbol.services;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.TipoDeTorneo;
import ar.com.nybble.futbol.Torneo;

/**
 * @author notarip
 *
 */
public interface AbmTorneoService {
	
	
	/**
	 * @param nombre
	 * @param cantidadClubs
	 * @param tipoDeTorneo
	 * @param fecha
	 */
	public void crearTorneo(String nombre, Iterator<Club> clubs, Integer cantidadClubs,TipoDeTorneo tipoDeTorneo, Date fecha);
	
	/**
	 * @param id
	 * @return Torneo
	 */
	public Torneo buscarTorneo(Long id);
	
	
	/**
	 * @param torneo
	 */
	public void modificarTorneo(Torneo torneo);
	
	
	/**
	 * @return Iterator<Torneo>
	 */
	public Iterator<Torneo> buscarTorneos();
	
	

}
