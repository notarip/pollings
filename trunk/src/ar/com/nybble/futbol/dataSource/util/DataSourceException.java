/**
 * 
 */
package ar.com.nybble.futbol.dataSource.util;

/**
 * @author Administrador
 *
 */
public class DataSourceException extends Exception {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataSourceException() {
        super();
    }
    
    public DataSourceException(String message) {
        super(message);
    }
    
    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DataSourceException(Throwable cause) {
        super(cause);
    }

}
