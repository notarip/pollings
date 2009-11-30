/**
 * 
 */
package ar.com.nybble;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Pablo
 *
 */
public class PruebasDeFecha {
	
	
	
	/**
	 * Crear una fecha 
	 */
	@Test
	public void crearUnaFecha() {
		Fecha fecha = new Fecha("01/01/1982");
		assertEquals(fecha,new Fecha("01/01/1982"));
	}
	
	
	/**
	 * Agregar dias a la Fecha
	 * TODO Implementar agregarDias a la fecha bien 
	 */
	@Test
	public void AgregarDiasALaFecha() {
		Fecha fecha = new Fecha("01/01/1982");
		Fecha fecha2 = fecha.agregarDias(10);
		assertEquals(fecha2, new Fecha("10/01/1982"));
	}
	
	
	
	//TODO Extraer dia de la fecha
	//TODO Extrer Mes de la fecha
	//TODO Extraer año de la fecha
	//TODO Comparar Fechas
	

}
