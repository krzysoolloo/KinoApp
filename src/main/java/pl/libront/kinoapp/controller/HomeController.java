package pl.libront.kinoapp.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.sql.DataSource;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import pl.libront.kinoapp.service.*;

/**
 * Handles requests for the application home page.
 */
@Controller 
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	MoviesService mService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {

		//user = rest.getForObject("http://localhost:8080/kinorest", Student[].class);
		/*String s = new String("asdf12sfdg4sdfg4");
		int suma = 0;
		int liczba = 0;
		boolean znacznik = false;
		String nowy = new String();
		for(int i=0; i<s.length(); i++){
			boolean war = s.charAt(i)>='0' && s.charAt(i)<='9';
			if(war)
			{
				nowy += s.charAt(i);
				
				znacznik = true;
			}
			
			if((war && (i==s.length()-1)) || (!war && znacznik == true))
			{
				System.out.println(nowy);
				suma += Integer.parseInt(nowy);
				nowy = new String();
				znacznik = false;
			}
		}*/
		
		model.addAttribute("polecane", mService.getMoviePolecane());
		//model.addAttribute("users", user );
		
		return "homepage";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {
		return "adminpage";
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Locale locale, Model model) throws JsonGenerationException, JsonMappingException, IOException {
		return "userpage";
	}
		
}
