package ec.edu.ups.dao;

import java.util.List;

import ec.edu.ups.modelo.Vehiculo;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Ticket;

public interface GenericDAO <T, ID> {


	public void create(T entity);

	public T read(ID id);

	public void update(T entity);

	public void delete(T entity);

	public List<T> find();

	Vehiculo read(Cliente cliente);

	List<Vehiculo> findByCliente(Cliente cliente);
	
	Ticket readVehiculo(Vehiculo vehiculo);

	Ticket readVehiculo(String numero);

}