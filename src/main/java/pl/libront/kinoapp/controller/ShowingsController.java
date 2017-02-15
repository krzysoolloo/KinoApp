package pl.libront.kinoapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.Movies;
import pl.libront.kinoapp.model.Reservations;
import pl.libront.kinoapp.model.Showings;
import pl.libront.kinoapp.model.User;
import pl.libront.kinoapp.service.CinemaHallsService;
import pl.libront.kinoapp.service.MoviesService;
import pl.libront.kinoapp.service.ReservationsService;
import pl.libront.kinoapp.service.ShowingsService;
import pl.libront.kinoapp.service.UserService;


@Controller
public class ShowingsController {

	@Autowired
	ShowingsService sService;
	
	@Autowired
	MoviesService mService;
	
	@Autowired
	CinemaHallsService cService;
	
	@Autowired
	ReservationsService rService;
	
	@Autowired
	UserService uService;

	@RequestMapping(value = "/admin/showings", method = RequestMethod.GET)
	public String showShowings(WebRequest request, Model model) {
		
		List<Showings> cin = sService.getShowings();
		model.addAttribute("cos", "wiekszcos");
		model.addAttribute("showings", cin);
	    return "showings";
	}
	
	@RequestMapping(value = "/admin/showings/add", method = RequestMethod.GET)
	public String addShow(WebRequest request, Model model) {
		
		List<Movies> movieList = mService.getMovies();
		List<CinemaHalls> hallsList = cService.getCinemaHalls();

		model.addAttribute("movieList", movieList);
		model.addAttribute("hallsList", hallsList);
		
		return "addshowings";
	}
		
	@RequestMapping(value = "/admin/showings/add", method = RequestMethod.POST)
	public String addMovies(@RequestParam("movieid") Integer movie_id,
				            @RequestParam("hallid") Integer hall_id,
				            @RequestParam("datetime") String datetime,
				            WebRequest request, Model model) {
		
		
		List<Movies> movieList = mService.getMovies();
		List<CinemaHalls> hallsList = cService.getCinemaHalls();
		
		Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try
        {
            date = simpleDateFormat.parse(datetime);
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
		
        
        if(sService.checkTime(date, movie_id, hall_id, 0))
        {
        	sService.addShowings(new Showings(null, mService.getMovie(movie_id), cService.getCinemaHall(hall_id), date));
    		model.addAttribute("message", "Seans został dodany.");
        }
        else
        {
    		model.addAttribute("message", "W tym czasie odbywa się już seans w tej sali.");
        }
		//System.out.println("\n\n" + movie_id + " " + hall_id + " " + date);
		model.addAttribute("movieList", movieList);
		model.addAttribute("hallsList", hallsList);
	    return "addshowings";
	}
		
	@RequestMapping(value = "/admin/showings/edit/{id}", method = RequestMethod.GET)
	public String editMovies(@PathVariable("id") Integer id, WebRequest request, Model model) {
		
		Showings show = sService.getShow(id);
		List<Movies> movieList = mService.getMovies();
		List<CinemaHalls> hallsList = cService.getCinemaHalls();

		model.addAttribute("movieList", movieList);
		model.addAttribute("hallsList", hallsList);
		model.addAttribute("showings", show);
		
	    return "editshowings";
	}
	
	@RequestMapping(value = "/admin/showings/edit/{id}", method = RequestMethod.POST)
	public String editMovies(@PathVariable("id") Integer id, @RequestParam("movieid") Integer movie_id,
            @RequestParam("hallid") Integer hall_id,
            @RequestParam("datetime") String datetime, WebRequest request, Model model) {
		
		Showings show = sService.getShow(id);
		List<Movies> movieList = mService.getMovies();
		List<CinemaHalls> hallsList = cService.getCinemaHalls();
		
		Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try
        {
            date = simpleDateFormat.parse(datetime);
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }
		
        if(sService.checkTime(date, movie_id, hall_id, show.getId()))
        {
            sService.update(show, cService.getCinemaHall(hall_id), mService.getMovie(movie_id), date);
    		model.addAttribute("message", "Seans został edytowany.");
        }
        else
        {
    		model.addAttribute("message", "W tym czasie odbywa się już seans w tej sali.");
        }
        
        //sService.addShowings(new Showings(null, mService.getMovie(movie_id), cService.getCinemaHall(hall_id), date));
        
		//System.out.println("\n\n" + movie_id + " " + hall_id + " " + date);
		model.addAttribute("movieList", movieList);
		model.addAttribute("hallsList", hallsList);

		model.addAttribute("showings", show);
	    return "editshowings";
	}
	
	@RequestMapping(value = "/admin/showings/delete/{id}", method = RequestMethod.GET)
	public String deleteMovies(@PathVariable("id") Integer id, WebRequest request, Model model) {
		sService.deleteShowings(id);
	    return "redirect:/admin/showings";
	}
	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Integer id, WebRequest request, Model model) {
		Showings show = sService.getShow(id);
		if(show == null || new Date().after(show.getDate()))
		{
			return "redirect:/";
		}
		else
		{
			model.addAttribute("show", show);
			model.addAttribute("reservations", rService.getReservations(id));
		    return "show";
		}
	}
	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.POST)
	public String showPOST(@PathVariable("id") Integer id, @RequestParam("zaznaczone") String zaznaczone, WebRequest request, Model model) {
		Showings show = sService.getShow(id);
		if(show == null || new Date().after(show.getDate()))
		{
			return "redirect:/";
		}
		else
		{
			
			String [] lista = zaznaczone.split(",");
			System.out.println("\n\nDUPA: \n"+zaznaczone+"\n");
			System.out.println("\n\nDUPA: \n");
			for(int i = 0; i < lista.length; i++){
				String [] l = lista[i].split("_");
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				Reservations r = new Reservations(null, Integer.parseInt(l[0]), Integer.parseInt(l[1]), show, uService.get(auth.getName()));
				rService.save(r);
				System.out.println(l[0]+" "+l[1]+"\n");
			}
			model.addAttribute("show", show);
			model.addAttribute("reservations", rService.getReservations(id));
		    return "redirect:/reservations";
		}
	}
	
	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public String showReservations(WebRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Reservations> reservations = rService.getUserReservations(auth.getName());

			model.addAttribute("reservations", reservations);
		    return "reservations";
	}
}
