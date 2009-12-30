/**
 * 
 */
package ar.com.nybble.futbol.services;

import java.util.Date;

import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Nacionalidad;

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
	 */
	public void crearJugador (String nombre, Date fechaDeNacimiento, Nacionalidad nacionalidad,
			 Documento documento);
	
	
	

}
