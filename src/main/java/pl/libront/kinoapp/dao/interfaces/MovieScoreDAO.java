package pl.libront.kinoapp.dao.interfaces;

import java.util.List;

import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.MovieScore;

public interface MovieScoreDAO {

	MovieScore findById(int id);
	
	public void saveOrUpdate(MovieScore student);
	
	public void deleteById(int contactId);
		
	public List<MovieScore> list();
	
	List<MovieScore> findAllMovieScore();
}
