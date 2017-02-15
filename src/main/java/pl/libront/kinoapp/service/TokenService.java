package pl.libront.kinoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.libront.kinoapp.dao.interfaces.TokenDAO;
import pl.libront.kinoapp.model.Tokens;

@Service("TokenService")
public class TokenService {

	@Autowired
	TokenDAO tokenDao;
	
	public void addToken(Tokens token) {
		tokenDao.saveOrUpdate(token);
		
	}

	public Boolean checkToken(String email, String token) {
		
		if(tokenDao.checkToken(email, token)!=null)
			return true;
		else
			return false;
	}

}
