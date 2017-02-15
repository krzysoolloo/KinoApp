package pl.libront.kinoapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.libront.kinoapp.dao.interfaces.TokenDAO;
import pl.libront.kinoapp.model.Tokens;
import pl.libront.kinoapp.model.User;
@Repository("TokenDAO")
@Transactional
public class TokenDAOImpl extends AbstractDAO<Integer, Tokens> implements TokenDAO {

	@Override
	public void saveOrUpdate(Tokens token) {
		saveOrUpdate(token);
		
	}

	@Override
	public Tokens checkToken(String email, String token) {

		List<Tokens> tokens = new ArrayList<Tokens>();

		tokens = getSession()
			.createQuery("from Tokens where email=? and token=?")
			.setParameter(0, email)
			.setParameter(1, token)
			.list();

		if (tokens.size() > 0) {
			return tokens.get(0);
		} else {
			return null;
		}

	}

}
