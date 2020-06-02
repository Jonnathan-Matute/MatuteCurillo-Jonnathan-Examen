package ec.edu.ups.jpa;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.edu.ups.dao.VehiculoDAO;
import ec.edu.ups.modelo.Vehiculo;
import ec.edu.ups.modelo.Cliente;

public class JPAVehiculoDAO extends JPAGenericDAO<Vehiculo, Integer> implements VehiculoDAO {

	public JPAVehiculoDAO() {
		super(Vehiculo.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Vehiculo> findByCliente(Cliente cliente) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Vehiculo> criteriaQuery = criteriaBuilder.createQuery(Vehiculo.class);
		Root<Vehiculo> root = criteriaQuery.from(Vehiculo.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("cliente"), cliente));
		System.out.println(em.createQuery(criteriaQuery).getResultList());
		return em.createQuery(criteriaQuery).getResultList();

	}

	@Override
	public Vehiculo read(String numero) {
		Query query = em.createNamedQuery("read");
		query.setParameter("cliente", numero);
		Vehiculo vehiculo = (Vehiculo) query.getSingleResult();
		return vehiculo;
	}
}
