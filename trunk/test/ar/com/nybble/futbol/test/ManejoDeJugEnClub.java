/**
 * 
 */
package ar.com.nybble.futbol.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.com.nybble.futbol.common.exceptions.JugadorNoEsDelClubException;
import ar.com.nybble.futbol.common.exceptions.JugadorYaFichadoPorElClubException;
import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Jugador;

/**
 * @author notarip
 *
 */
public class ManejoDeJugEnClub {
	
	Club boca = new Club("BOCA JUNIORS");
	Jugador pablo = new Jugador(new Documento("29317973", TipoDeDocumento.DNI));
	Jugador juan = new Jugador(new Documento("29317974",TipoDeDocumento.DNI));
	Jugador pedro = new Jugador(new Documento("29317975",TipoDeDocumento.DNI));
	 
	
	/**
	 * Agregar un jugador a un club 
	 */
	@Test
	public void agregarUnJugadorAUnClub() {
		boca.ficharJugador(pablo);
		assertTrue(boca.perteneceAlClub(pablo));
	}
	
	
	
	/**
	 * Agregar varios jugadores a un club
	 */
	@Test
	public void agregarVariosJugadoresAUnClub() {
		boca.ficharJugador(pablo);
		boca.ficharJugador(juan);
		boca.ficharJugador(pedro);
		assertTrue(boca.perteneceAlClub(pablo));
		assertTrue(boca.perteneceAlClub(juan));
		assertTrue(boca.perteneceAlClub(pedro));
	}
	
		
	/**
	 * No poder fichar a un jugador que ya esta fichado
	 */
	@Test (expected = JugadorYaFichadoPorElClubException.class)
	public void noPoderFicharUnJugadorQueYaEstaFichado() {
		boca.ficharJugador(pablo);
		boca.ficharJugador(pablo);
	}
	
	/**
	 * Remover a un jugador de un club
	 */
	@Test
	public void removerAUnJugadorDeUnClub() {
		boca.ficharJugador(pablo);
		boca.rescindirContratoJugador(pablo);
		assertFalse(boca.perteneceAlClub(pablo));
	}
	

	/**
	 * que notifique si se quiere rescindir a un jugador que no es del club
	 */
	@Test(expected= JugadorNoEsDelClubException.class)
	public void notifiqueSiQuiereRescindirUnJugadorQueNoEsDelClub() {
		boca.ficharJugador(pablo);
		boca.rescindirContratoJugador(pedro);

	}
	


}
