package pl.libront.kinoapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.libront.kinoapp.dao.interfaces.CinemaHallsDAO;
import pl.libront.kinoapp.dao.interfaces.MoviesDAO;
import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.Movies;

@Service("MoviesService")
@Transactional
public class MoviesService {
	
	@Autowired
	MoviesDAO mDAO;

    public List<Movies> getMovies() {
		return mDAO.findAllMovies();
    }

	public void addMovie(Movies movie) {
		mDAO.saveOrUpdate(movie);
		
	}
	
	public void editMovie(Movies movie) {
		mDAO.update(movie);
		
	}

	public void deleteMovie(Integer id) {
		mDAO.deleteById(id);
		
	}

	public Movies getMovie(Integer id) {
		return mDAO.findById(id);
	}

	public List<Movies> getMoviePolecane() {
		return mDAO.findPolecane();
	}
    
}
