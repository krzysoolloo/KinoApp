package pl.libront.kinoapp.dao.interfaces;

import java.util.List;
import pl.libront.kinoapp.model.Reservations;

public interface ReservationsDAO {
	List<Reservations> findById(int id);
	
	Reservations findByShow(int id);
	
	public void saveOrUpdate(Reservations reservation);
	
	public void deleteById(int contactId);
		
	public List<Reservations> list();

	void save(Reservations r);

	List<Reservations> getUserReservations(String name);

}
