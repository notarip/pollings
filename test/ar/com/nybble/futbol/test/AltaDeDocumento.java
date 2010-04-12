/**
 * 
 */
package ar.com.nybble.futbol.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.com.nybble.futbol.Documento;

/**
 * @author notarip
 *
 */
public class AltaDeDocumento {
	
	String nro = "29317973";
	Documento doc1 = new Documento(nro, TipoDeDocumento.DNI);
	

	/**
	 *que guarde el nro de documento 
	 */
	@Test
	public void queGuardeElNroDeDocumento() {
		assertTrue(doc1.getNumero() == nro);
	}
	
	
	/**
	 * que guarde el tipo de documento
	 */
	@Test
	public void queGuardeElTipoDeDocumento() {
		assertEquals(TipoDeDocumento.DNI, doc1.getTipoDeDocumento());

	}
	
	
	/**
	 * que compare dos documentos iguales
	 */
	@Test
	public void queCompareDosDocumentosIguales(){
		Documento doc2 = new Documento(nro, TipoDeDocumento.DNI);
		assertEquals(doc2, doc1);
	}
	
	/**
	 * que compare dos documentos con nros distintos
	 */
	@Test
	public void queCompareDosDocumentosConNrosDistintos(){
		Documento doc2 = new Documento("29317976", TipoDeDocumento.DNI);
		assertFalse(doc2.equals(doc1));
	}

	/**
	 * que compare dos documentos con tipos distintos
	 */
	@Test
	public void queCompareDosDocumentosConTiposDistintos(){
		Documento doc2 = new Documento(nro, TipoDeDocumento.LC);
		assertFalse(doc2.equals(doc1));
	}

	
	
	



	
	

}
