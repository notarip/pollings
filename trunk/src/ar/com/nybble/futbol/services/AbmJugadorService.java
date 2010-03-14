/**
 * 
 */
package ar.com.nybble.futbol.services;

import java.util.Date;
import java.util.Iterator;

import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.Nacionalidad;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;

/**
 * @author notarip
 *
 */
/**
 * @author Administrador
 *
 */
/**
 * @author Administrador
 *
 */
public interface AbmJugadorService {
	
	
	
	/**
	 * Se crea un nuevo jugador con los parametros recibidos
	 * @param nombre del jugador (NOMBRE/S APELLIDO)
	 * @param fechaDeNacimiento 
	 * @param nacionalidad 
	 * @param documento 
	 * @throws DataSourceException 
	 */
	public void crearJugador (String nombre, Date fechaDeNacimiento, Nacionalidad nacionalidad,
			 Documento documento) throws DataSourceException;

	
	/**
	 * Busca un jugador por id
	 * @param id
	 * @return Jugador
	 */
	public Jugador buscarJugador(Long id);
	
	/**
	 * Busca a todos los Jugadores
	 * @return Iterator<Jugador>
	 */
	public Iterator<Jugador> buscarJugadores();
	
	/**
	 * Busca a todos los jugadores pertenecientes a un club
	 * 
	 * @param idClub
	 * @return Iterator<Jugador>
	 */
	public Iterator<Jugador> buscarJugadoresPorClub(Long idClub);
	
	/**
	 * @param nombre
	 * @return Iterator<Jugador>
	 */
	public Iterator<Jugador> buscarJugadoresPorNombre(String nombre); 
		
	
	/**
	 * Guarda al jugador
	 * @param jugador
	 */
	public void modificarJugador(Jugador jugador);
	
	
	
		
	}

