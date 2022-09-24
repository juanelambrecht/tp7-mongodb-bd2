package ar.unrn.tp.web;

public class TarjetaDTO {
	private String digito;
	private String descripcion;
	private String banco;
	private double saldo;

	public TarjetaDTO(String digito, String descripcion, String banco, double saldo) {
		super();
		this.digito = digito;
		this.descripcion = descripcion;
		this.banco = banco;
		this.saldo = saldo;
	}

	public String getDigito() {
		return digito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getBanco() {
		return banco;
	}

	public double getSaldo() {
		return saldo;
	}

}
