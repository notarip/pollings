
package ar.com.nybble.futbol.dataSource.repositorio;

import java.io.Serializable;
import java.util.Collection;

import ar.com.nybble.futbol.dataSource.util.DataSourceException;


/**
 * @author msaladino
 *   
 *
 */
public interface Repository {
    
    // Actualizaci�n y creaci�n
    public void create(Object entity) throws DataSourceException;
    
    public void update(Object entity) throws DataSourceException; 
    
    // Buscadores
    public Collection findAll() throws DataSourceException;

    public Object findById(Serializable id) throws DataSourceException;
    
    public Object findById(Long id) throws DataSourceException;

}