package pl.libront.kinoapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import pl.libront.kinoapp.dao.interfaces.CinemaHallsDAO;
import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.Movies;
import pl.libront.kinoapp.model.User;

@Repository("CinemaHallsDAO")
public class CinemaHallsDAOImpl extends AbstractDAO<Integer, CinemaHalls> implements CinemaHallsDAO {

    @SuppressWarnings("unchecked")
    public List<CinemaHalls> findAllCinemaHalls() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<CinemaHalls> users = (List<CinemaHalls>) criteria.list();
        return users;
    }

	@Override
	public CinemaHalls findById(int id) {
		CinemaHalls myObject = (CinemaHalls) getSession().get(CinemaHalls.class, id);
		return myObject;
	}

	@Override
	public void saveOrUpdate(CinemaHalls cinema) {
		getSession().saveOrUpdate(cinema);
		
	}

	@Override
	public void deleteById(int id) {
	
		CinemaHalls myObject = (CinemaHalls)getSession().load(CinemaHalls.class,new Integer(id));
		getSession().delete(myObject);

	    //This makes the pending delete to be done
		getSession().flush() ;
		
	}

	@Override
	public List<CinemaHalls> list() {
		// TODO Auto-generated method stub
		return null;
	}
}