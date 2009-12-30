/**
 * 
 */
package ar.com.nybble.futbol.services;

import java.util.Date;

import ar.com.nybble.futbol.Documento;
import ar.com.nybble.futbol.Nacionalidad;
import ar.com.nybble.futbol.dataSource.repositorio.ClubRepositorio;
import ar.com.nybble.futbol.dataSource.repositorio.DocumentoRepositorio;
import ar.com.nybble.futbol.dataSource.repositorio.JugadorRepositorio;
import ar.com.nybble.futbol.dataSource.repositorio.NacionalidadRepositorio;

/**
 * @author notarip
 *
 */
final class AbmJugadorServiceImpl implements AbmJugadorService {
	
	private JugadorRepositorio jugadorRepo;
	private ClubRepositorio clubRepo;
	private DocumentoRepositorio documentoRepo;
	private NacionalidadRepositorio nacionalidadRepo;
	
	public AbmJugadorServiceImpl(JugadorRepositorio jugadorRepo, ClubRepositorio clubRepo,
			DocumentoRepositorio documentoRepo, NacionalidadRepositorio nacionalidadRepo) {
		this.jugadorRepo = jugadorRepo;
		this.clubRepo = clubRepo;
		this.documentoRepo = documentoRepo;
		this.nacionalidadRepo = nacionalidadRepo;
	}

	
	
	public JugadorRepositorio getJugadorRepo() {
		return jugadorRepo;
	}


	public ClubRepositorio getClubRepo() {
		return clubRepo;
	}


	public DocumentoRepositorio getDocumentoRepo() {
		return documentoRepo;
	}


	public NacionalidadRepositorio getNacionalidadRepo() {
		return nacionalidadRepo;
	}


	public void setJugadorRepo(JugadorRepositorio jugadorRepo) {
		this.jugadorRepo = jugadorRepo;
	}


	public void setClubRepo(ClubRepositorio clubRepo) {
		this.clubRepo = clubRepo;
	}


	public void setDocumentoRepo(DocumentoRepositorio documentoRepo) {
		this.documentoRepo = documentoRepo;
	}


	public void setNacionalidadRepo(NacionalidadRepositorio nacionalidadRepo) {
		this.nacionalidadRepo = nacionalidadRepo;
	}

	@Override
	public void crearJugador(String nombre, Date fechaDeNacimiento,
			Nacionalidad nacionalidad, Documento documento) {
				
		
	}

}
