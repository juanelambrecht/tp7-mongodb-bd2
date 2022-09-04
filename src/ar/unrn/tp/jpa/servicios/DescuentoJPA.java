package ar.unrn.tp.jpa.servicios;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ar.unrn.tp.api.DescuentoService;
import ar.unrn.tp.modelo.PromocionCompra;
import ar.unrn.tp.modelo.PromocionProducto;
import ar.unrn.tp.modelo.Promociones;
import ar.unrn.tp.modelo.Tarjetas;

public class DescuentoJPA implements DescuentoService {

	@Override
	public void crearDescuentoSobreTotal(Date fechaDesde, Date fechaHasta, String tarjeta) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			PromocionCompra promo1 = new PromocionCompra(fechaDesde, fechaHasta, tarjeta);
			em.persist(promo1);

			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();

			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}

	}

	@Override
	public void crearDescuento(Date fechaDesde, Date fechaHasta, String marca) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Promociones promo1 = new PromocionProducto(fechaDesde, fechaHasta, marca);
			em.persist(promo1);

			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
		}

	}

}
