/**
 * 
 */
package ar.com.nybble.futbol.services;


import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.TipoDeTorneo;
import ar.com.nybble.futbol.Torneo;
import ar.com.nybble.futbol.dataSource.repositorio.PartidoRepositorio;
import ar.com.nybble.futbol.dataSource.repositorio.TorneoRepositorio;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;
import ar.com.nybble.futbol.dataSource.util.TransactionalProxyFactory;

/**
 * @author notarip
 *
 */
public class AbmTorneoServiceImpl implements AbmTorneoService {

	
	private TorneoRepositorio torneoRepo;
	private PartidoRepositorio partidoRepo;
	
	
	public AbmTorneoServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Torneo buscarTorneo(Long id) {
		try {
			return (Torneo) torneoRepo.findById(id);
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Iterator<Torneo> buscarTorneos() {
		//TorneoRepositorio repo = (TorneoRepositorio) TransactionalProxyFactory.newInstance(torneoRepo);
		try {
			Iterator<Torneo> itera = torneoRepo.findAll().iterator();
			return  itera;
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Iterator buscarTorneoPorNombre(String nombre) {
		List torneos = new LinkedList<Torneo>();
		
		try {
			for (Iterator iterator = torneoRepo.findAll().iterator(); iterator.hasNext();) {
				Torneo torneo = (Torneo) iterator.next();
				if (torneo.getNombre().contains(nombre) || (torneo.getNombre().contains(nombre.toUpperCase())) ){
					torneos.add(torneo);
				}	
			}
		} catch (Exception e) {
		
			e.printStackTrace();
			}
		return torneos.iterator(); 

	}
	
	@Override
	public void crearTorneo(String nombre, Iterator<Club> clubs, Integer cantidadClubs,TipoDeTorneo tipoDeTorneo, Date fecha) {
		Torneo torneo = new Torneo(nombre, cantidadClubs, tipoDeTorneo, fecha);
		for (Iterator iterator =  clubs; iterator.hasNext();) {
			Club club = (Club) iterator.next();
			torneo.cargarClub(club);
		}
		torneo.habilitar(fecha);
		torneo.generarPartidos();
		TorneoRepositorio repo = (TorneoRepositorio) TransactionalProxyFactory.newInstance(torneoRepo);
		try {
			repo.create(torneo);
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void modificarTorneo(Torneo torneo) {
		TorneoRepositorio repo = (TorneoRepositorio) TransactionalProxyFactory.newInstance(torneoRepo);
		try {
			repo.update(torneo);
		} catch (DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}


	public void setTorneoRepo(TorneoRepositorio torneoRepo) {
		this.torneoRepo = torneoRepo;
	}


	public TorneoRepositorio getTorneoRepo() {
		return torneoRepo;
	}


	public void setPartidoRepo(PartidoRepositorio partidoRepo) {
		this.partidoRepo = partidoRepo;
	}


	public PartidoRepositorio getPartidoRepo() {
		return partidoRepo;
	}



}
