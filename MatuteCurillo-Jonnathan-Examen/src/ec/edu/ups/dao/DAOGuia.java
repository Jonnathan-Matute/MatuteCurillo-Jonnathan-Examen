package ec.edu.ups.dao;

import ec.edu.ups.jpa.JPADAOGuia;

public abstract class DAOGuia {
	
	protected static DAOGuia guia = new JPADAOGuia();
	/**
	 * @return the guia
	 */
	public static DAOGuia getGuia() {
		return guia;
	}

	public abstract ClienteDAO getClienteDAO();

	public abstract VehiculoDAO getVehiculoDAO();
	
	public abstract TicketDAO getTicketDAO();
	
}