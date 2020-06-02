package ec.edu.ups.jpa;

import javax.persistence.Query;

import ec.edu.ups.dao.ClienteDAO;
import ec.edu.ups.modelo.Cliente;

public class JPAClienteDAO extends JPAGenericDAO<Cliente, String> implements ClienteDAO{

	public JPAClienteDAO() {
		super(Cliente.class);
		
	}

	@Override
	public Cliente readByCedula(String id) {
		Query query = em.createNamedQuery("readByCedula");
		query.setParameter("nombre", id);
		Cliente cli = (Cliente) query.getSingleResult();
		return cli;
		
	}



}
