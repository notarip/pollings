/**
 * 
 */
package ar.com.nybble.futbol.dataSource.repositorio;

/**
 * @author notarip
 *
 */
public final class AbmClubRepositoryFactory {
	
	private static final ClubRepositorio clubRepo = new ClubRepositorioImpl();
	
	
	public AbmClubRepositoryFactory() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static ClubRepositorio getClubrepo() {
		return clubRepo;
	}
	

}
