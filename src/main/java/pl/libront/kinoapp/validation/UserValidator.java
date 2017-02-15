package pl.libront.kinoapp.validation;


import pl.libront.kinoapp.model.User;
import pl.libront.kinoapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("UserValidator")
@Transactional
public class UserValidator implements Validator {

	UserService userService = new UserService();
	
    @Override
    public boolean supports(final Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "message.name", "Firstname is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "message.surname", "LastName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "message.password", "Password is required.");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matchingPassword", "message.matchingPassword", "matchingPassword is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "message.email", "email is required.");
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matchingEmail", "message.matchingEmail", "matchingEmail is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "message.phone", "Phone is required.");*/
        User user = (User) obj;

        if(!(user.getEmail().equals(user.getMatchingEmail())))
        	errors.rejectValue("matchingEmail", "message.matchingEmail", "Wpisane emaile różnią się od siebie.");
        else
        	if(userService.checkEmail(user.getEmail()))
            	errors.rejectValue("email", "message.email", "Ten email już istnieje w bazie danych.");
        
        if(!(user.getPassword().equals(user.getMatchingPassword())))
        	errors.rejectValue("matchingPassword", "message.matchingPassword", "Wpisane hasła różnią się od siebie.");
    }

}