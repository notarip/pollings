package ar.com.nybble.futbol.dataSource.repositorio;

import ar.com.nybble.futbol.Club;
import ar.com.nybble.futbol.dataSource.util.DataSourceException;

public class ClubRepositorioImpl extends RepositoryImpl implements ClubRepositorio {

    public ClubRepositorioImpl() {
        super(Club.class);
    }
    
    public Club retrieve(Long id) throws DataSourceException {
        return (Club) this.findById(id);
    }
    
}
