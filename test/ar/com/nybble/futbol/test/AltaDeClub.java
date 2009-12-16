/**
 * 
 */
package ar.com.nybble.futbol.test;
import org.junit.Test;

import ar.com.nybble.futbol.Club;
import static org.junit.Assert.*;
/**
 * @author Administrador
 *
 */
public class AltaDeClub {
	
	

	/**
	 * Dar de alta un club y que lo guarde
	 */
	@Test
	public void darDeAltaUnClubQueLoGuarde() {
		Club club = new Club("MILAN");
		assertTrue(club.toString()=="MILAN");

	}
	
	

}
