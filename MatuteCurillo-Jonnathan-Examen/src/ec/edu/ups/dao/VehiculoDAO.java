package ec.edu.ups.dao;

import ec.edu.ups.modelo.Vehiculo;

public interface VehiculoDAO extends GenericDAO<Vehiculo, Integer> {

	public abstract Vehiculo read(String numero);

}