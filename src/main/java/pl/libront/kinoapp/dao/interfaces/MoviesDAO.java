package pl.libront.kinoapp.dao.interfaces;

import java.util.List;

import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.Movies;

public interface MoviesDAO {

	Movies findById(int id);
	
	public void saveOrUpdate(Movies student);
	
	public void update(Movies student);
	
	public void deleteById(int contactId);
		
	public List<Movies> list();
	
	List<Movies> findAllMovies();

	List<Movies> findPolecane();
}
