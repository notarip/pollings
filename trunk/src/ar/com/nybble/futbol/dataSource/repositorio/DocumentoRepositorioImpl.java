/**
 * 
 */
package ar.com.nybble.futbol.dataSource.repositorio;

import ar.com.nybble.futbol.Documento;

/**
 * @author notarip
 *
 */
public class DocumentoRepositorioImpl extends RepositoryImpl implements
		DocumentoRepositorio {

	public DocumentoRepositorioImpl() {
		super(Documento.class);
	}

}
