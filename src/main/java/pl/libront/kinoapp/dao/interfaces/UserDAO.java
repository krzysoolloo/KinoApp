package pl.libront.kinoapp.dao.interfaces;

import pl.libront.kinoapp.model.Tokens;
import pl.libront.kinoapp.model.User;

public interface UserDAO {
	User findByUserEmail(String email);
	Integer saveUser(User user);
	void changePassword(Tokens token);
	User get(String email);
}
