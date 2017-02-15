package pl.libront.kinoapp.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.libront.kinoapp.dao.interfaces.UserDAO;
import pl.libront.kinoapp.model.Tokens;
import pl.libront.kinoapp.model.User;

@Repository("UserDAO")
@Transactional
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO {

	@SuppressWarnings("unchecked")
	public User findByUserEmail(String username) {

		List<User> users = new ArrayList<User>();

		users = getSession()
			.createQuery("from User where email=?")
			.setParameter(0, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	
	public Integer saveUser(User user) {
		//persist(user);
		save(user);
		
		return 1;

	}

	@Override
	public void changePassword(Tokens token) {
		//System.out.println(token.getEmail()+" "+token.getPassword()+ " " + token.getToken());
		List<User> users = new ArrayList<User>();

		users = getSession()
			.createQuery("from User where email=?")
			.setParameter(0, token.getEmail())
			.list();
		users.get(0).setMatchingEmail(token.getEmail());
		users.get(0).setMatchingPassword(token.getPassword());
		users.get(0).setPassword(token.getPassword());
		//System.out.println(users.get(0).getPassword());
		getSession()
				.createQuery("delete from Tokens where email=:ii")
				.setParameter("ii", token.getEmail()).executeUpdate();
		//getSession().delete(token);
		//.setPassword(token.getPassword());
	
	}

	@Override
	public User get(String email) {
		List<User> users = new ArrayList<User>();

		users = getSession()
			.createQuery("from User where email=?")
			.setParameter(0, email)
			.list();
		return users.get(0);
	}
}
