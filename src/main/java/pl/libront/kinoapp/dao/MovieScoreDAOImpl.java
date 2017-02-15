package pl.libront.kinoapp.dao;

import java.util.List;

import pl.libront.kinoapp.dao.interfaces.MovieScoreDAO;
import pl.libront.kinoapp.model.MovieScore;
import pl.libront.kinoapp.model.Movies;

public class MovieScoreDAOImpl extends AbstractDAO<Integer, MovieScore> implements MovieScoreDAO {

	@Override
	public MovieScore findById(int id) {
		MovieScore myObject = (MovieScore)getSession().load(MovieScore.class,new Integer(id));
		return myObject;
	}

	@Override
	public void saveOrUpdate(MovieScore ms) {
		saveOrUpdate(ms);
	}

	@Override
	public void deleteById(int contactId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MovieScore> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieScore> findAllMovieScore() {
		// TODO Auto-generated method stub
		return null;
	}

}
