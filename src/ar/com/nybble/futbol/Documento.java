package ar.com.nybble.futbol;

import javax.persistence.Entity;
import javax.persistence.Id;

import ar.com.nybble.futbol.test.TipoDeDocumento;

@Entity
public class Documento {

	
	
	
	private String numero;
	private TipoDeDocumento tipo;

	public Documento(String numero, TipoDeDocumento tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}

	@Id
	public String getNumero() {
		return numero;
	}

	public TipoDeDocumento getTipoDeDocumento() {
		return tipo;
	}
	
	
	public TipoDeDocumento getTipo() {
		return tipo;
	}
	
	@Override
	public boolean equals(Object otroDoc) {
		if (this.numero == ((Documento) otroDoc).getNumero() && 
		this.tipo == ((Documento) otroDoc).getTipo())
			return true;
		else
			return false;
	}

	
	


}
