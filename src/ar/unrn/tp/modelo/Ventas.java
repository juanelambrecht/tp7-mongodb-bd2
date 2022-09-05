package ar.unrn.tp.modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ventas {
	@Id
	@GeneratedValue
	private Long id;
	Date fecha;
	LocalTime hora;
	Clientes cliente;
	private ArrayList<Productos> productos = new ArrayList<Productos>();
	double montoTotal;

	public Ventas() {

	}

	public Ventas(Date fecha, Clientes cliente, ArrayList<Productos> productos, double montoTotal) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.productos = productos;
		this.montoTotal = montoTotal;
	}

	@Override
	public String toString() {
		return "Ventas [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", cliente=" + cliente + ", productos="
				+ productos + ", montoTotal=" + montoTotal + "]";
	}

}
