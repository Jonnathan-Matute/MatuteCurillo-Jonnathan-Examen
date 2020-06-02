package ec.edu.ups.modelo;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vehiculo
 *
 */

@NamedQuery(name = "read", query = "SELECT v FROM Vehiculo v WHERE v.placa = :cliente")
@NamedQuery(name = "findByCliente", query = "SELECT v FROM Vehiculos v WHERE v.cliente.cedula = :cedula")

@Entity
public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String placa;
	private String marca;
	private String modelo;
	@ManyToOne
	@JoinColumn
	private Cliente cliente;
	
	public Vehiculo() {
	}

	public Vehiculo(String placa, String marca, String modelo, Cliente cliente) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cliente = cliente;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehiculos [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", cliente=" + cliente + "]";
	}

	public Object getfechaHoraIngeso() {
		// TODO Auto-generated method stub
		return null;
	}

}
