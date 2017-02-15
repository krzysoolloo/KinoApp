package pl.libront.kinoapp.dao.interfaces;

import java.util.Date;
import java.util.List;

import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.MovieScore;
import pl.libront.kinoapp.model.Movies;
import pl.libront.kinoapp.model.Showings;
import pl.libront.kinoapp.model.ShowsDate;

public interface ShowingsDAO {
	Showings findById(int id);
	
	public void saveOrUpdate(Showings student);
	
	public void deleteById(int contactId);
		
	public List<Showings> list();
	
	List<Showings> findAllShowings();

	void update(Showings movie, CinemaHalls cinemaHalls, Movies movies, Date date);

	List<Showings> getShowingsByDate(String data);
	
	boolean checkTime(Date date, int movie_id, int hall_id, int show_id);

	List<ShowsDate> getShowingsGroup(Integer id);
}
