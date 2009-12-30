/**
 * 
 */
package ar.com.nybble.futbol.dataSource.repositorio;

/**
 * @author notarip
 *
 */
public final class AbmJugadorRepositoryFactory {
	public AbmJugadorRepositoryFactory() {
	}

	
	private static final JugadorRepositorio jugadorRepo = new JugadorRepositorioImpl();
	private static final ClubRepositorio clubRepo = new ClubRepositorioImpl();
	private static final DocumentoRepositorio documentoRepo = new DocumentoRepositorioImpl();
	private static final NacionalidadRepositorio nacionalidadRepo = new NacionalidadRepositorioImpl();
	
	
	public static JugadorRepositorio getJugadorRepo() {
		return jugadorRepo;
	}

	public static DocumentoRepositorio getDocumentoRepo() {
		return documentoRepo;
	}


	public static ClubRepositorio getClubRepo() {
		return clubRepo;
	}

	public static NacionalidadRepositorio getNacionalidadRepo() {
		return nacionalidadRepo;
	}
	


}
