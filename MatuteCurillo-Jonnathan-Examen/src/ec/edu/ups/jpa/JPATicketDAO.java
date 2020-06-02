package ec.edu.ups.jpa;

import javax.persistence.Query;
import ec.edu.ups.dao.TicketDAO;
import ec.edu.ups.modelo.Ticket;

public class JPATicketDAO extends JPAGenericDAO<Ticket, Integer> implements TicketDAO {

	public JPATicketDAO() {
		super(Ticket.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ticket readVehiculo(String numero) {
		Query query = em.createNamedQuery("readVehiculo");
		query.setParameter("cliente", numero);
		Ticket ticket = (Ticket) query.getSingleResult();
		return ticket;
	}

	@Override
	public Ticket read(int numero) {
		// TODO Auto-generated method stub
		return null;
	}
}
