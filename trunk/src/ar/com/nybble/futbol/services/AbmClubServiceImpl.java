/**
 * 
 */
package ar.com.nybble.futbol.services;

import java.util.Iterator;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Nacionalidad;
import ar.com.nybble.futbol.dataSource.repositorio.ClubRepositorio;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.dataSource.util.TransactionalProxyFactory;

/**
 * @author notarip
 * 
 */
public class AbmClubServiceImpl implements AbmClubService {

	
	private ClubRepositorio clubRepo;

	
	
	public AbmClubServiceImpl(ClubRepositorio clubrepo) {
		this.setClubRepo(clubrepo);
	}

	public AbmClubServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public void setClubRepo(ClubRepositorio clubRepo) {
		this.clubRepo = clubRepo;
	}

	public ClubRepositorio getClubRepo() {
		return clubRepo;
	}
	
	@Override
	public void crearClub(String nombre) {
		Club club = new Club(nombre);
		ClubRepositorio repo =  (ClubRepositorio) TransactionalProxyFactory.newInstance(clubRepo);
		try {
			repo.create(club);
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public Club buscarClub(Long id) {
		ClubRepositorio repo = clubRepo; //(ClubRepositorio) TransactionalProxyFactory.newInstance(clubRepo);
		try {
			return (Club) repo.findById(id);
		} catch (DataSourceException e) {
			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public void modificarClub(Club club) {
		ClubRepositorio repo =  (ClubRepositorio) TransactionalProxyFactory.newInstance(clubRepo);
		try {
			repo.update(club);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Iterator<Club> buscarClubs() {
		try {
			return clubRepo.findAll().iterator();
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
