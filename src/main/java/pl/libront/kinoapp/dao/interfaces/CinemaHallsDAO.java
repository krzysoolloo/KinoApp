package pl.libront.kinoapp.dao.interfaces;

import java.util.List;

import pl.libront.kinoapp.model.CinemaHalls;

public interface CinemaHallsDAO {

	CinemaHalls findById(int id);
	
	public void saveOrUpdate(CinemaHalls student);
	
	public void deleteById(int contactId);
		
	public List<CinemaHalls> list();
	
	List<CinemaHalls> findAllCinemaHalls();
}
