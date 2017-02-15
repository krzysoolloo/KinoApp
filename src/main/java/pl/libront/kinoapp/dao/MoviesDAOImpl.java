package pl.libront.kinoapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import pl.libront.kinoapp.dao.interfaces.CinemaHallsDAO;
import pl.libront.kinoapp.dao.interfaces.MovieScoreDAO;
import pl.libront.kinoapp.dao.interfaces.MoviesDAO;
import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.MovieScore;
import pl.libront.kinoapp.model.Movies;
import pl.libront.kinoapp.model.Showings;
import pl.libront.kinoapp.model.Tokens;
import pl.libront.kinoapp.model.User;

@Repository("MoviesDAO")
public class MoviesDAOImpl extends AbstractDAO<Integer, Movies> implements MoviesDAO {

    @SuppressWarnings("unchecked")
    public List<Movies> findAllMovies() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Movies> users = (List<Movies>) criteria.list();

        return users;
    }
    
    @SuppressWarnings("unchecked")
    public List<Movies> findPolecane() {
    	List<Movies> tokens = null;

    	Query query = getSession().createSQLQuery(
				"select * from movies where recommended = '1' ORDER BY id DESC LIMIT 9")
				.addEntity(Movies.class);
    	tokens = (List<Movies>)query.list();

		if (tokens.size() > 0) {
			return tokens;
		} else {
			return null;
		}
    }

	@Override
	public Movies findById(int id) {
		
		Movies myObject = (Movies) getSession().get(Movies.class, id);
		Hibernate.initialize(myObject.getMovieScore());
		Hibernate.initialize(myObject.getShowings());
		//System.out.println(myObject.getMovieScore());
		return myObject;
	}

	@Override
	public void saveOrUpdate(Movies movie) {
		if(movie.getRecommended() == null)
			movie.setRecommended("0");
		boolean znacznik = false;
		if(movie.getId()==null)znacznik = true;
		
		getSession().saveOrUpdate(movie);
			/*MovieScore ms = new MovieScore(null, movie.getId(), 0, 0);
			MovieScoreDAO msdao = new MovieScoreDAOImpl();
			msdao.saveOrUpdate(ms);*/
		System.out.println(movie.getId()+" "+movie.getTime()+" "+movie.getRecommended());
		if(znacznik){
		MovieScore ms = new MovieScore(null, movie.getId(), 0, 0);
		movie.setMovieScore(ms);
		}
	}
	
	@Override
	public void update(Movies movie) {
		if(movie.getRecommended() == null)
			movie.setRecommended("0");
		getSession().saveOrUpdate(movie);
	}

	@Override
	public void deleteById(int id) {
	
		Movies myObject = (Movies)getSession().load(Movies.class,new Integer(id));
		//getSession().delete(myObject.getMovieScore());
		getSession().delete(myObject);
	  
	}

	@Override
	public List<Movies> list() {
		// TODO Auto-generated method stub
		return null;
	}

}