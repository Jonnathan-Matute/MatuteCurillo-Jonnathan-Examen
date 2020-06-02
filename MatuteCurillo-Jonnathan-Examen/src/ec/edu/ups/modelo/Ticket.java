package ec.edu.ups.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ticket
 *
 */

@NamedQuery(name = "readVehiculo", query = "SELECT t FROM Ticket t WHERE t.numero = :cliente")
@NamedQuery(name = "readTicket", query = "SELECT t FROM Ticket t WHERE t.numero = :cliente")
@NamedQuery(name = "findByCliente", query = "SELECT t FROM Ticket t WHERE t.cliente.cedula = :cedula")

@Entity
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String numero;
	private String fechaHoraIngeso;
	private String fechaHoraSalida;
	@ManyToOne
	@JoinColumn
	private Vehiculo vehiculos;
	private Cliente clientes;
	
	public Ticket() {
	}

	public Ticket(String numero, String fechaHoraIngeso, String fechaHoraSalida, Vehiculo vehiculos, Cliente clientes) {
		super();
		this.numero = numero;
		this.fechaHoraIngeso = fechaHoraIngeso;
		this.fechaHoraSalida = fechaHoraSalida;
		this.vehiculos = vehiculos;
		this.setClientes(clientes);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String nuevonumero) {
		this.numero = nuevonumero;
	}

	public String getFechaHoraIngeso() {
		return fechaHoraIngeso;
	}

	public void setFechaHoraIngeso(String fechaHoraIngeso) {
		this.fechaHoraIngeso = fechaHoraIngeso;
	}

	public String getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(String fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public Vehiculo getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Vehiculo vehiculos) {
		this.vehiculos = vehiculos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Ticket other = (Ticket) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public Cliente getClientes() {
		return clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return "Ticket [numero=" + numero + ", fechaHoraIngeso=" + fechaHoraIngeso + ", fechaHoraSalida="
				+ fechaHoraSalida + ", vehiculos=" + vehiculos + ", clientes=" + clientes + "]";
	}

}
