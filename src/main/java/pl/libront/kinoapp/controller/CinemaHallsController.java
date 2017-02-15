package pl.libront.kinoapp.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import pl.libront.kinoapp.model.CinemaHalls;
import pl.libront.kinoapp.model.User;
import pl.libront.kinoapp.service.CinemaHallsService;


@Controller
public class CinemaHallsController {
	
	@Autowired
	CinemaHallsService chService;

	@RequestMapping(value = "/admin/cinemahalls", method = RequestMethod.GET)
	public String showCinemaHalls(WebRequest request, Model model) {
		
		List<CinemaHalls> cin = chService.getCinemaHalls();
		
		CinemaHalls cinemahalls = new CinemaHalls();
		model.addAttribute("cinemahalls", cinemahalls);
		model.addAttribute("cinemahallss", cin);
	    return "cinemahalls";
	}
	
	@RequestMapping(value = "/admin/cinemahalls", method = RequestMethod.POST)
	public String addCinemaHalls(@ModelAttribute("CinemaHalls") CinemaHalls newhall, WebRequest request, Model model) {
		
		chService.addCinemaHalls(newhall);
		
		List<CinemaHalls> cin = chService.getCinemaHalls();
		
		CinemaHalls cinemahalls = new CinemaHalls();
		model.addAttribute("cinemahalls", cinemahalls);
		model.addAttribute("cinemahallss", cin);
	    return "cinemahalls";
	}
	
	@RequestMapping(value = "/admin/cinemahalls/delete/{id}", method = RequestMethod.GET)
	public String addCinemaHalls(@PathVariable("id") Integer id, WebRequest request, Model model) {
		chService.deleteCinemaHalls(id);
	    return "redirect:/admin/cinemahalls";
	}
	
}
