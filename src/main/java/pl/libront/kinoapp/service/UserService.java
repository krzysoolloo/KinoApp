package pl.libront.kinoapp.service;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pl.libront.kinoapp.dao.interfaces.UserDAO;
import pl.libront.kinoapp.model.Tokens;
import pl.libront.kinoapp.model.User;

@Service("UserService")
@Transactional
public class UserService {

	@Autowired
	UserDAO userDao;

	
    public void saveUser(User user) throws JsonGenerationException, JsonMappingException, IOException {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setMatchingPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(0);
		user.setEnabled(1);
		userDao.saveUser(user);
    }
    
	public Boolean checkEmail(String email) {
		if(userDao.findByUserEmail(email)!=null)
			return true;
		else
			return false;
	}

	public void changePassword(Tokens token) {
		userDao.changePassword(token);
		
	}

	public void changePassword(String email, String token, String password) {
		Tokens tokn = new Tokens(email, token, password);
		userDao.changePassword(tokn);
	}
	
	public User get(String email) {
		return userDao.get(email);
	}
}
