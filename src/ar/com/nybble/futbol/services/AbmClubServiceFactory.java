/**
 * 
 */
package ar.com.nybble.futbol.services;

import ar.com.nybble.futbol.dataSource.repositorio.AbmClubRepositoryFactory;


/**
 * @author notarip
 *
 */
public final class AbmClubServiceFactory {
	
	private final static AbmClubService abmClub = new AbmClubServiceImpl(AbmClubRepositoryFactory.getClubrepo());

	public static AbmClubService getAbmclub() {
		return abmClub;
	}

}
