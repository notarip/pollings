/**
 * 
 */
package ar.com.nybble.futbol;

import java.util.Date;

/**
 * @author notarip
 *
 */
public interface Persona {
	
	/**
	 * @param nombre
	 */
	void setNombre(String nombre);
	
	/**
	 * @return
	 */
	String getNombre();
	
	/**
	 * @param fecha
	 */
	void setFechaNacimiento(Date fecha);
	
	/**
	 * @return
	 */
	Date getFechaNacimieto();

	/**
	 * @param documento
	 */
	void setDocumento(Documento documento);
	
	/**
	 * @return
	 */
	Documento getDocumento();
}
