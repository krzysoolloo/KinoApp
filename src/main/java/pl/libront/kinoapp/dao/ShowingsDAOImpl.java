package pl.libront.kinoapp.dao;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.libront.kinoapp.dao.interfaces.ShowingsDAO;
import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.Movies;
import pl.libront.kinoapp.model.Showings;
import pl.libront.kinoapp.model.ShowsDate;
import pl.libront.kinoapp.model.ShowsTime;
import pl.libront.kinoapp.service.MoviesService;

@Repository("ShowingsDAO")
public class ShowingsDAOImpl extends AbstractDAO<Integer, Showings> implements ShowingsDAO {
	
	@Autowired
	MoviesService mService;
	
	public Showings findById(int id) {
		
		Showings myObject = (Showings) getSession().get(Showings.class, id);
;
		return myObject;
	}

	@Override
	public void saveOrUpdate(Showings movie) {
		getSession().saveOrUpdate(movie);
			/*MovieScore ms = new MovieScore(null, movie.getId(), 0, 0);
			MovieScoreDAO msdao = new MovieScoreDAOImpl();
			msdao.saveOrUpdate(ms);*/

	}

	@Override
	public void deleteById(int id) {
		Showings myObject = new Showings();
		myObject.setId(id);
		getSession().delete(myObject);
	}

	@Override
	public List<Showings> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Showings> findAllShowings() {
	      Criteria criteria = createEntityCriteria().addOrder(Order.desc("date"));
	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        List<Showings> users = (List<Showings>) criteria.list();

	        return users;
	}

	@Override
	public List<Showings> getShowingsByDate(String data) {
		Criteria criteria = getSession().createCriteria(Showings.class);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date startDate = null;
		try {
			startDate = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(startDate); 
		c.add(Calendar.DATE, 1);
		Date endDate = c.getTime();
		
		/*
		if(startDate!=null){
			criteria.add(Expression.ge("date",startDate));
		}
		if(endDate!=null){
			criteria.add(Expression.le("date",endDate));
		}
		criteria.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("movie")));
		 
		criteria.addOrder(Order.asc("id"));*/
			
		Query query = getSession().createSQLQuery(
				"select * from showings s LEFT JOIN movies m ON s.movie_id = m.id WHERE s.date > :start AND s.date < :end  AND s.date > NOW() GROUP BY s.id, m.id ORDER BY s.date ASC")
				.addEntity(Showings.class)
				.setParameter("start", startDate)
				.setParameter("end", endDate);

 
		return (List<Showings>)query.list();
        //return showings;
	}

	@Override
	public boolean checkTime(Date date, int movie_id, int hall_id, int show_id) {
		Date startDate = date;

		String time = mService.getMovie(movie_id).getTime();
		
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.MINUTE, Integer.parseInt(time));  // number of days to add
		Date endDate = c.getTime();

		Query query = getSession().createSQLQuery(
				"select * from showings s LEFT JOIN movies m ON s.movie_id = m.id WHERE ((s.date+ interval '1 minutes'*CAST(m.time AS int)) > :start AND s.date < :end ) AND s.cinema_halls_id = :id AND s.id != :sid")
				
				//"select * from showings s LEFT JOIN movies m ON s.movie_id = m.id WHERE s.date > :start AND s.date < :end")
				.addEntity(Showings.class)
				.setParameter("id", hall_id)
				.setParameter("sid", show_id)
				.setParameter("start", startDate)
				.setParameter("end", endDate);
			
		int count = query.list().size();
		
		if(count>0)
			return false;
		else
			return true;
	}

	@Override
	public List<ShowsDate> getShowingsGroup(Integer id) {
		Query query = getSession().createSQLQuery(
				"SELECT CAST(date AS DATE) AS data from showings WHERE movie_id = :id and date > NOW() GROUP BY data ORDER BY data ASC")
				.setParameter("id", id);
		

		List<ShowsDate> sd = new ArrayList<ShowsDate>();
		
		for(int i =0; i<query.list().size(); i++)
		{
			sd.add(new ShowsDate((Date)(query.list().get(i))));
			Query query2 = getSession().createSQLQuery(
					"SELECT id, CAST(date AS TIME) AS time from showings where movie_id = :id AND DATE(date) = :dat ORDER BY time ASC")
					.setParameter("id", id)
					.setParameter("dat", (Date)query.list().get(i));
			List<ShowsTime> st = new ArrayList<ShowsTime>();
			
			List<Object[]> li= (List<Object[]>)query2.list();
			
			//sd.get(sd.size()-1).setTime(st);
			
				for(Object[] ob: li)
				{ 
					st.add(new ShowsTime(Integer.parseInt(ob[0].toString()), ob[1].toString()));
				}
				sd.get(sd.size()-1).setTime(st);
				//System.out.println(st.get(j).getId()+"\n\n");

			
		}
 
		return sd;
	}

	@Override
	public void update(Showings movie, CinemaHalls cinemaHalls, Movies movies, Date date) {
		movie.setCinemaHalls(cinemaHalls);
		movie.setMovie(movies);
		movie.setDate(date);
		getSession().saveOrUpdate(movie);
	}
}
