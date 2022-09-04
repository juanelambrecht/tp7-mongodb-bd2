package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Clientes;
import ar.unrn.tp.modelo.OrdenDePago;
import ar.unrn.tp.modelo.Productos;
import ar.unrn.tp.modelo.Promociones;
import ar.unrn.tp.modelo.Tarjetas;
import ar.unrn.tp.modelo.Ventas;

public class VentaJPA implements VentaService {

	@Override
	public void realizarVenta(Long idCliente, List<Long> productos, Long idTarjeta) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Clientes cliente = em.find(Clientes.class, idCliente);
			Tarjetas tarjeta = em.find(Tarjetas.class, idTarjeta);

			TypedQuery<Productos> productosQuery = em.createQuery("select p from Productos p where p.id in :id",
					Productos.class);
			productosQuery.setParameter("id", productos);
			List<Productos> productosCompra = productosQuery.getResultList();

			TypedQuery<Promociones> promociones = em.createQuery(
					"select p from Promociones p where " + "?1 between p.fechaInicio and p.fechaFin",
					Promociones.class);
			promociones.setParameter(1, new Date(), TemporalType.DATE);
			List<Promociones> listaPromociones = promociones.getResultList();

			Carrito carrito = new Carrito((ArrayList<Promociones>) listaPromociones);
			carrito.agregarListaProductos((ArrayList<Productos>) productosCompra);
			carrito.agregarTarjeta(tarjeta);

			Ventas venta = carrito.realizarCompra(cliente);
			em.persist(venta);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}

	}

	@Override
	public float calcularMonto(List<Long> productos, Long idTarjeta) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
		EntityManager em = emf.createEntityManager();
		// EntityTransaction tx = em.getTransaction();
		try {
			TypedQuery<Promociones> promociones = em.createQuery(
					"select p from Promociones p where " + "?1 between p.fechaInicio and p.fechaFin",
					Promociones.class);
			promociones.setParameter(1, new Date(), TemporalType.DATE);
			List<Promociones> listaPromociones = promociones.getResultList();

			TypedQuery<Productos> productosQuery = em.createQuery("select p from Productos p where p.id in :id",
					Productos.class);
			productosQuery.setParameter("id", productos);
			List<Productos> listaProductos = productosQuery.getResultList();

			Carrito carrito = new Carrito((ArrayList<Promociones>) listaPromociones);
			carrito.agregarListaProductos((ArrayList<Productos>) listaProductos);

			Tarjetas tarjeta = em.find(Tarjetas.class, idTarjeta);
			carrito.agregarTarjeta(tarjeta);
			return (float) carrito.calculoPrecioTotal();

		} catch (Exception e) {
			// tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}

	}

	@Override
	public List<Ventas> ventas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Ventas> ventas = em.createQuery("select v from Ventas v", Ventas.class);
			return ventas.getResultList();
		} catch (Exception e) {
			// tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}
	}

}
