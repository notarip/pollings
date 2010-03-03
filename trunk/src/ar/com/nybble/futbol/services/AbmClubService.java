/**
 * 
 */
package ar.com.nybble.futbol.services;

import java.util.Collection;
import java.util.Iterator;

import ar.com.nybble.futbol.Club;

/**
 * @author notarip
 *
 */
public interface AbmClubService {
	
	
	
	/**
	 * Se crea un nuevo club
	 * @param nombre
	 */
	public void crearClub (String nombre); 
	
	/**
	 * Busca un club por id
	 * @param id
	 * @return
	 */
	public Club buscarClub(Long id);
	
	/**
	 * Actualiza un club existente
	 * @param club
	 */
	public void modificarClub(Club club);
	
	/**
	 * Devuelve un iterador con los clubs
	 * @return
	 */
	public Iterator<Club> buscarClubs();
	

}
