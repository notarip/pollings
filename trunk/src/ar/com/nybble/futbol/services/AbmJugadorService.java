/**
 * 
 */
package ar.com.nybble.futbol.services;

import java.util.Date;

import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.Nacionalidad;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;

/**
 * @author notarip
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

	public Jugador buscarJugador(Long id);

	public void modificarJugador(Jugador jugador);
		
	}
