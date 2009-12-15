package ar.com.nybble.futbol.dataSource.repositorio;

import ar.com.nybble.futbol.Jugador;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;


/**
 *
 * @author msaladino
 */
final class JugadorRepositorioImpl extends RepositoryImpl implements JugadorRepositorio{
    
    JugadorRepositorioImpl() {
        super(Jugador.class);
    }
    
    public Jugador retrieve(Long id) throws DataSourceException {
        return (Jugador) this.findById(id);
    }
    
}
