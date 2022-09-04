package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.Date;

public class Carrito {

	private ArrayList<Productos> productos = new ArrayList<Productos>();
	private Tarjetas tarjeta;
	private double montoTotal;
	private ArrayList<Promociones> promocion = new ArrayList<Promociones>();

	public Carrito(ArrayList<Promociones> promocion) {
		super();
		this.promocion = promocion;
	}

	public void agregarProductos(Productos p) {
		this.productos.add(p);
	}

	public void agregarListaProductos(ArrayList<Productos> p) {
		this.productos = p;
	}

	public void agregarTarjeta(Tarjetas tarjeta) {
		this.tarjeta = tarjeta;
	}

	public void agregarMarca(Marca marca) {
	}

	public double calculoDescuento() {
		double total = 0;
		for (Promociones prom : this.promocion) {
			total += prom.calculoPromocion(this.montoTotal, this.productos, this.tarjeta.getBanco());
		}
		return total;
	}

	public double calculoPrecioTotal() {
		double total = 0;
		for (Productos prod : this.productos) {
			total += prod.getPrecio();
		}
		double descuento = this.calculoDescuento();
		System.out.println(descuento);
		return total - descuento;
	}

	public Ventas realizarCompra(Clientes cliente) {
		double total = this.calculoPrecioTotal();
		Ventas venta = new Ventas(new Date(), cliente, this.productos, total);
		return venta;
	}

}
