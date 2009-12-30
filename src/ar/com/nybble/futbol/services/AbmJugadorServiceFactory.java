/**
 * 
 */
package ar.com.nybble.futbol.services;

import ar.com.nybble.futbol.dataSource.repositorio.AbmJugadorRepositoryFactory;

/**
 * @author notarip
 *
 */
public final class AbmJugadorServiceFactory {

	
	private final static AbmJugadorService abmJugador = new AbmJugadorServiceImpl(AbmJugadorRepositoryFactory.getJugadorRepo(),
			AbmJugadorRepositoryFactory.getClubRepo(),AbmJugadorRepositoryFactory.getDocumentoRepo(),AbmJugadorRepositoryFactory.getNacionalidadRepo());
			
	public AbmJugadorServiceFactory() {
		
	}

	public static AbmJugadorService getAbmjugador() {
		return abmJugador;
	}

}
