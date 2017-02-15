package pl.libront.kinoapp.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pl.libront.kinoapp.dao.interfaces.ReservationsDAO;
import pl.libront.kinoapp.model.Reservations;
import pl.libront.kinoapp.model.Showings;

@Repository("ReservationsDAO")
public class ReservationsDAOImpl extends AbstractDAO<Integer, Reservations> implements ReservationsDAO {

	@Override
	public List<Reservations> findById(int id) {
		Query query = getSession().createSQLQuery(
				"select * from reservations where showing_id = :id")
				.addEntity(Reservations.class)
				.setParameter("id", id);

 
		return (List<Reservations>)query.list();

	}

	@Override
	public void saveOrUpdate(Reservations reservation) {
		getSession().saveOrUpdate(reservation);
		
	}

	@Override
	public void deleteById(int contactId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservations> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservations findByShow(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void save(Reservations reservation) {
		getSession().save(reservation);
		
	}

	@Override
	public List<Reservations> getUserReservations(String name) {
		Query query = getSession().createSQLQuery(
				"select * from reservations where user_id = :id")
				.addEntity(Reservations.class)
				.setParameter("id", name);

 
		return (List<Reservations>)query.list();
	}

}
