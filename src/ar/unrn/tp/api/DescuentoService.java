package ar.unrn.tp.api;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import ar.unrn.tp.modelo.Productos;
import ar.unrn.tp.modelo.Promociones;
import ar.unrn.tp.modelo.Tarjetas;

public interface DescuentoService {
	// validar que las fechas no se superpongan
	void crearDescuentoSobreTotal(Date fechaDesde, Date fechaHasta, String Tarjeta);

	// validar que las fechas no se superpongan
	void crearDescuento(Date fechaDesde, Date fechaHasta, String marca);

	// Devuelve todos los productos
	List<Promociones> listarDescuentos();
}
