/**
 * 
 */
package ar.com.nybble.futbol.dataSource.repositorio;

import java.io.Serializable;
import java.util.Collection;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.Torneo;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;

/**
 * @author notarip
 *
 */
public class TorneoRepositorioImpl extends RepositoryImpl implements TorneoRepositorio {

	protected TorneoRepositorioImpl() {
		super(Torneo.class);

	}
	
    public Torneo retrieve(Long id) throws DataSourceException {
        return (Torneo) this.findById(id);
    }

	
}
