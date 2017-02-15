package pl.libront.kinoapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import pl.libront.kinoapp.dao.interfaces.MoviesDAO;
import pl.libront.kinoapp.dao.interfaces.ShowingsDAO;
import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.Movies;
import pl.libront.kinoapp.model.Showings;
import pl.libront.kinoapp.model.ShowsDate;

@Service("ShowingsService")
@Transactional
public class ShowingsService {
	@Autowired
	ShowingsDAO sDAO;
	
	public List<Showings> getShowings() {  	
		return sDAO.findAllShowings();
    }

	public void addShowings(Showings newshow) {
		sDAO.saveOrUpdate(newshow);
	}

	public void deleteShowings(Integer id) {
		sDAO.deleteById(id);
	}

	public Showings getShow(Integer id) {
		return sDAO.findById(id);
	}

	public void editShow(Showings newshow) {
		//sDAO.update(newshow);
	}

	public List<Showings> getShowingsByDate(String data) {
		return sDAO.getShowingsByDate(data);
		
	}
	
	public boolean checkTime(Date date, int movie_id, int hall_id, int show_id) {
		return sDAO.checkTime(date, movie_id, hall_id, show_id);
		
	}

	public List<ShowsDate> getShowingsGroup(Integer id) {
		return sDAO.getShowingsGroup(id);
		
	}

	public void update(Showings show, CinemaHalls cinemaHalls, Movies movies, Date date) {
		sDAO.update(show, cinemaHalls, movies, date);
		
	}

}
