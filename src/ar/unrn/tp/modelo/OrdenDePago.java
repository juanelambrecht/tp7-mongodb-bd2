package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrdenDePago {
	@Id
	@GeneratedValue
	private Long id;
	private Date fechaOrden;
	@ManyToOne(fetch = FetchType.EAGER)
	private Clientes cliente;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Productos> productos;
	private double total;

	public OrdenDePago() {

	}

	public OrdenDePago(Date fechaOrden, Clientes cliente, ArrayList<Productos> productos, double total) {
		super();
		this.fechaOrden = fechaOrden;
		this.productos = productos;
		this.total = total;
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "OrdenDePago [fechaOrden=" + fechaOrden + ", cliente=" + cliente + ", productos=" + productos
				+ ", total=" + total + "]";
	}

}
