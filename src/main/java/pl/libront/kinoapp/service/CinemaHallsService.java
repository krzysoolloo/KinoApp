package pl.libront.kinoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import pl.libront.kinoapp.dao.interfaces.CinemaHallsDAO;
import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.Movies;

@Service("CinemaHallsService")
@Transactional
public class CinemaHallsService {
	
	@Autowired
	CinemaHallsDAO chDAO;

    public List<CinemaHalls> getCinemaHalls() {
		return chDAO.findAllCinemaHalls();
    }

	public void addCinemaHalls(CinemaHalls cinema) {
		chDAO.saveOrUpdate(cinema);
		
	}

	public void deleteCinemaHalls(Integer id) {
		chDAO.deleteById(id);
		
	}

	public CinemaHalls getCinemaHall(Integer id) {
		return chDAO.findById(id);
	}
    
}
