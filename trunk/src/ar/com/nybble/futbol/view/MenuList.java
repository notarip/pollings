package ar.com.nybble.futbol.view;

import java.util.ArrayList;

public class MenuList extends ArrayList<Object> {
	private String nombre;
	
	public MenuList() {
		// TODO Auto-generated constructor stub
	}
	
	public MenuList(String nombre) {
		super();
		setNombre(nombre);
	}
	
	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	
	

}
