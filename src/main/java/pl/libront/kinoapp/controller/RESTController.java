package pl.libront.kinoapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.libront.kinoapp.service.ShowingsService;
import pl.libront.kinoapp.model.Movies;
import pl.libront.kinoapp.model.Showings;

@RestController
public class RESTController {
	
	@Autowired
	ShowingsService sService;
	
	@RequestMapping(value = "/getshowings/{data:.+}", method = RequestMethod.GET)
	public List<Showings> showUser(@PathVariable("data") String data) {
	
		//UserDetails users = userService.loadUserByUsername("admin@kino.pl");
		List<Showings> ls = sService.getShowingsByDate(data);

		return ls;
	}
}
