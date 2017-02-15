package pl.libront.kinoapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import pl.libront.kinoapp.core.MailMail;
import pl.libront.kinoapp.model.Tokens;
import pl.libront.kinoapp.model.User;
import pl.libront.kinoapp.service.TokenService;
import pl.libront.kinoapp.service.UserService;
import pl.libront.kinoapp.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userService;
	
	@Autowired
	@Qualifier("UserService")
	UserService uService;
	
	@Autowired
	@Qualifier("TokenService")
	TokenService tService;
	

	UserValidator userValidator = new UserValidator();
		
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Locale locale, Model model)
	{
		if (error != null) {
			model.addAttribute("msg", "Podane dane są nieprawidłowe");
		}
		if (logout != null) {
			model.addAttribute("msg", "Zostałeś wylogowany.");
		}
		return "login";
	
	}

	
	@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		User userDto = new User();
	    model.addAttribute("user", userDto);
	    return "registration";
	}
	
	
	
	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("user") User userForm, BindingResult bindingResult, Model model) throws JsonGenerationException, JsonMappingException, IOException {

		userValidator.validate(userForm, bindingResult);
		

        if (bindingResult.hasErrors()) {
        	//if(!(userForm.getPassword().equals(userForm.getMatchingPassword())))
        	//model.addAttribute("users", bindingResult.toString());
            return "registration";
        }

        uService.saveUser(userForm); 
                
        model.addAttribute("users", userForm);
		return "registrationok";
    }
	
	@RequestMapping(value = "/user/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(WebRequest request, Model model) {

	    return "forgotpassword";
	}
	
	@RequestMapping(value = "/user/forgotpassword", method = RequestMethod.POST)
	public String sendMail(WebRequest request, Model model) {
		
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("Spring-Mail.xml");
	    	 String token = new Md5PasswordEncoder().encodePassword(request.getParameter("email"), "super");
	    	MailMail mm = (MailMail) context.getBean("mailMail");
	        mm.sendMail("krzysztof.libront@gmail.com",
	        		request.getParameter("email"),
	    		   "Odzyskiwanie hasła", 
	    		   "Aby zresetować swoje hasło przejdź na ten adres: \n http://localhost:14234/kinoapp/user/newpassword/"+request.getParameter("email")+"/"+token);
	       
	        
	        
	        
	        model.addAttribute("message", "W celu zmiany hasła sprawdź swoją pocztę.");
	        Tokens tokn = new Tokens(request.getParameter("email"), token);
	        //System.out.println(tokn);
	        //uService.resetPassword(tokn);
	        tService.addToken(tokn);
	        
	        
	    return "forgotpassword";
	}
	
	@RequestMapping(value = "/user/newpassword/{email:.+}/{token}", method = RequestMethod.GET)
	public String newPassword(@PathVariable("email") String email, @PathVariable("token") String token, Model model) {
		
		if(tService.checkToken(email, token))
			return "newpassword";
		else
			return "403";
	}
	
	@RequestMapping(value = "/user/newpassword/{email:.+}/{token}", method = RequestMethod.POST)
	public String addNewPassword(@PathVariable("email") String email, @PathVariable("token") String token, WebRequest request, Model model) {
		if(tService.checkToken(email, token))
		{
			if(!request.getParameter("password").equals(request.getParameter("matchingPassword")))
			{
				model.addAttribute("message", "Podane hasła nie są takie same.");
				return "newpassword";
			}
			else
			{
				uService.changePassword(email, token, request.getParameter("password"));
				return "redirect:/user/login";
			}
		}
		else
			return "403";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(Model model) {


		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addAttribute("username", userDetail.getUsername());

		}

		return "403";

	}
	
}
