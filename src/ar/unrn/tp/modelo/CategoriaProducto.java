package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CategoriaProducto {
	@Id
	@GeneratedValue
	private Long id;
	private String nombreCategoria, descripcion;

	public CategoriaProducto() {
		// TODO Auto-generated constructor stub
	}

	public CategoriaProducto(String nombreCategoria, String descripcion) {
		super();
		this.nombreCategoria = nombreCategoria;
		this.descripcion = descripcion;
	}

	public String descripcionCategoria() {
		return descripcion;
	}

}
