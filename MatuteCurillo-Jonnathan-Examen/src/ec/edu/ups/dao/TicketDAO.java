package ec.edu.ups.dao;

import ec.edu.ups.modelo.Ticket;

public interface TicketDAO extends GenericDAO<Ticket, Integer> {

	public abstract Ticket read(int numero);

}