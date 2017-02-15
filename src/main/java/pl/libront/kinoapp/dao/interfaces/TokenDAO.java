package pl.libront.kinoapp.dao.interfaces;

import pl.libront.kinoapp.model.Tokens;

public interface TokenDAO {
	public void saveOrUpdate(Tokens token);

	public Tokens checkToken(String email, String token);
}
