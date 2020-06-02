package ec.edu.ups.dao;

import ec.edu.ups.modelo.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente, String>{
	
	public abstract Cliente readByCedula(String id);
	
		

}
