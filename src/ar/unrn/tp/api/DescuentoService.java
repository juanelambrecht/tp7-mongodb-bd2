package ar.unrn.tp.api;

import java.time.LocalDate;
import java.util.Date;

import ar.unrn.tp.modelo.Tarjetas;

public interface DescuentoService {
	// validar que las fechas no se superpongan
	void crearDescuentoSobreTotal(Date fechaDesde, Date fechaHasta, String Tarjeta);

	// validar que las fechas no se superpongan
	void crearDescuento(Date fechaDesde, Date fechaHasta, String marca);

}
