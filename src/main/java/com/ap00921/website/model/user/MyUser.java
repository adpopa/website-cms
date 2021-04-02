/**
 * User.java
 */
package com.ap00921.website.model.user;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
@Document(collection = "users")
public class MyUser {

	@Id
	@Getter
	private String userId;
	
	@Email(message = "Username needs to be an email")
	@NotEmpty(message = "Username is required")
	@Indexed(unique = true)
	@Getter
	@Setter
	private String username;
	
	@NotEmpty(message = "Password cannot be blank")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{10,40}$", message = "The password must contain at least 1 lowercase, 1 uppercase, 1 digit and one special character: @#$%^&+=!. At least 10 characters long")
	@Getter
	@Setter
	private String password;
	
	@NotEmpty(message = "Password cannot be blank")
	@Getter
	@Setter
	@Transient
	private String confirmPassword;

	@Getter
	@Setter
	private String usertype;
	
	@DBRef
	@Getter
	@Setter
	private Role role;
	
	@Getter
	@Setter
	private Date createdAt;
	
	@Getter
	@Setter
	private Date updatedAt;

	/**
	 * @param username
	 * @param password
	 * @param confirmPassword
	 */
	public MyUser(
			@Email(message = "Username needs to be an email") @NotEmpty(message = "Username is required") String username,
			@NotEmpty(message = "Password cannot be blank") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{10,40}$", message = "The password must contain at least 1 lowercase, 1 uppercase, 1 digit and one special character: @#$%^&+=!. At least 10 characters long") String password,
			@NotEmpty(message = "Password cannot be blank") String confirmPassword) {
		this.userId = new ObjectId().toHexString();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public MyUser() {
	}
	
}
