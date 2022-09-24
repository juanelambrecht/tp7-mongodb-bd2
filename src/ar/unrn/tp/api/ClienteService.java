package ar.unrn.tp.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.unrn.tp.modelo.Clientes;
import ar.unrn.tp.modelo.Tarjetas;

public interface ClienteService {
	// validar que el dni no se repita
	void crearCliente(String nombre, String apellido, String dni, String email);

	// validar que sea un cliente existente
	void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email);

	// validar que sea un cliente existente
	void agregarTarjeta(Long idCliente, String digitos, String descripcion, String banco, double saldo);

	// Devuelve las tarjetas de un cliente espec�fico
	List<Tarjetas> listarTarjetas(Long idCliente);

	// Devuelve las tarjetas de un cliente espec�fico
	List<Clientes> listarClientes();
}
