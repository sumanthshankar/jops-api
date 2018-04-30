package edu.calstatela.jobsapi.validators;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.calstatela.jobsapi.model.User;
import edu.calstatela.jobsapi.repository.UserRepository;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserRepository userRepository;
	
	Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            		  Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		User userEmailCheck = userRepository.findByemailId(user.getEmailId());

		if(userEmailCheck != null) {
			if(user.getEmailId().equals(userEmailCheck.getEmailId()))
				errors.rejectValue("emailId", "error.field.emailId");
		}
		
		if (!(pattern.matcher(user.getEmailId()).matches())) 
			errors.rejectValue("emailId", "error.field.emailId"); 
		
		if(!StringUtils.hasText(user.getPassword()))
			errors.rejectValue("password", "error.field.password");
	
		if(!user.getPassword().equals(user.getConfirmPassword()))
			errors.rejectValue("confirmPassword", null, "Password does not match");
	}

}
