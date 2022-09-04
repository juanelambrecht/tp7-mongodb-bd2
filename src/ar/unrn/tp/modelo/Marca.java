package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Marca {
	@Id
	@GeneratedValue
	private Long id;
	private String nombreMarca, descripcion;

	public Marca() {
	}

	public Marca(String nombreMarca, String descripcion) {
		super();
		this.nombreMarca = nombreMarca;
		this.descripcion = descripcion;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

}
