package ec.edu.ups.jpa;

import ec.edu.ups.dao.DAOGuia;
import ec.edu.ups.dao.TicketDAO;
import ec.edu.ups.dao.VehiculoDAO;
import ec.edu.ups.dao.ClienteDAO;

public class JPADAOGuia extends DAOGuia {

	

	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return new JPAClienteDAO();
	}

	@Override
	public VehiculoDAO getVehiculoDAO() {
		// TODO Auto-generated method stub
		return new JPAVehiculoDAO();
	}
	
	@Override
	public TicketDAO getTicketDAO() {
		// TODO Auto-generated method stub
		return new JPATicketDAO();
	}
	

}
