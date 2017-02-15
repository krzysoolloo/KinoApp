package pl.libront.kinoapp.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.Movies;
import pl.libront.kinoapp.model.Showings;
import pl.libront.kinoapp.model.ShowsDate;
import pl.libront.kinoapp.service.CinemaHallsService;
import pl.libront.kinoapp.service.MoviesService;
import pl.libront.kinoapp.service.ShowingsService;

@Controller
public class MoviesController {
	@Autowired
	MoviesService mService;
	
	@Autowired
	ShowingsService sService;

	@RequestMapping(value = "/admin/movies", method = RequestMethod.GET)
	public String showMovies(WebRequest request, Model model) {
		
		List<Movies> cin = mService.getMovies();

		model.addAttribute("movies", cin);
	    return "movies";
	}
	
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public String showMovies2(WebRequest request, Model model) {
		
		List<Movies> cin = mService.getMovies();

		model.addAttribute("movies", cin);
	    return "movies2";
	}
	
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
	public String showMovie(@PathVariable("id") Integer id, WebRequest request, Model model) {
		
		Movies movie = mService.getMovie(id);
		//Set<Showings> showings = movie.getShowings();
		List<ShowsDate> showings = sService.getShowingsGroup(id);
		model.addAttribute("movie", movie);
		model.addAttribute("showings", showings);
	    return "movie";
	}
	
	@RequestMapping(value = "/admin/movies/add", method = RequestMethod.GET)
	public String addMovies(WebRequest request, Model model) {
		
		Movies movie = new Movies();

		model.addAttribute("movies", movie);
	    return "addmovies";
	}
	
	@RequestMapping(value = "/admin/movies/add", method = RequestMethod.POST)
	public String addMovies(@ModelAttribute("Movies") Movies newmovie, WebRequest request, Model model) {
		if(newmovie.getRecommended()==null)newmovie.setRecommended("0");
		mService.addMovie(newmovie);

		Movies movie = new Movies();
		model.addAttribute("movies", movie);
	    return "addmovies";
	}
	
	@RequestMapping(value = "/admin/movies/edit/{id}", method = RequestMethod.GET)
	public String editMovies(@PathVariable("id") Integer id, WebRequest request, Model model) {
		
		Movies movie = mService.getMovie(id);

		model.addAttribute("movies", movie);
	    return "editmovies";
	}
	
	@RequestMapping(value = "/admin/movies/edit/{id}", method = RequestMethod.POST)
	public String editMovies(@ModelAttribute("Movies") Movies newmovie, WebRequest request, Model model) {
		
		mService.editMovie(newmovie);

		model.addAttribute("movies", newmovie);
	    return "editmovies";
	}
	
	@RequestMapping(value = "/admin/movies/delete/{id}", method = RequestMethod.GET)
	public String deleteMovies(@PathVariable("id") Integer id, WebRequest request, Model model) {
		mService.deleteMovie(id);
	    return "redirect:/admin/movies";
	}
}
