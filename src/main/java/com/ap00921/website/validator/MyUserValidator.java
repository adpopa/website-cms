/**
 * MyUserValidator.java
 */
package com.ap00921.website.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ap00921.website.model.user.MyUser;

/**
 * @author Alex Daniel Popa
 *
 */
@Component
public class MyUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MyUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		MyUser user = (MyUser) target;

		if (user.getPassword().length() < 8) {
			errors.rejectValue("password", "Length", "Password must be at least 8 characters");
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Match", "Passwords must match");
		}

	}

}
