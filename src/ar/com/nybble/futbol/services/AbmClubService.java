/**
 * 
 */
package ar.com.nybble.futbol.services;

import java.util.Collection;

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
	

}
