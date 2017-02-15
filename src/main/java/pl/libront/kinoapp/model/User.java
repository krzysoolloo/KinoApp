package pl.libront.kinoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import pl.libront.kinoapp.annotation.ValidEmail;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "email", unique = true, 
		nullable = false, length = 45)
	@NotNull(message="{email.empty}")
	@NotEmpty(message="{email.empty}")
	@ValidEmail
	private String email;
	
	@Transient
	@NotNull(message="{matchingEmail.empty}")
    @NotEmpty(message="{matchingEmail.empty}")
    @ValidEmail
	private String matchingEmail;
	
	@Column(name = "password", 
			nullable = false, length = 60)
	@NotNull(message="{password.empty}")
    @NotEmpty(message="{password.empty}")
    @Size(min=6, max=60, message="{password.size}")
	private String password;
	
	@Transient
	@NotNull(message="{matchingPassword.empty}")
    @NotEmpty(message="{matchingPassword.empty}")
	private String matchingPassword;
	
	@Column(name = "name", nullable = false)
	@NotNull(message="{name.empty}")
    @NotEmpty(message="{name.empty}")
    @Size(min=3, max=45, message="{name.size}")
	private String name;
	
	@Column(name = "surname", nullable = false)
	@NotNull(message="{surname.empty}")
    @NotEmpty(message="{surname.empty}")
    @Size(min=2, max=45, message="{surname.size}")
	private String surname;
	
	@Column(name = "phone", nullable = false)
	@NotNull(message="{phone.empty}")
    @NotEmpty(message="{phone.empty}")
    @Size(min=11, max=11, message="{phone.size}")
	private String phone;
	
	@Column(name = "enabled", nullable = false)
	private Integer enabled;
	
	@Column(name = "role", nullable = false)
	private Integer role;

	public User() {
	}

	public User(String email, String password, String name, String surname, String phone, Integer enabled) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.enabled = enabled;
		this.role = 0;
	}
	
	public User(String email, String password, Integer enabled) {
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String email, String password, 
		Integer enabled, Integer role) {
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	public String getUsername() {
		return this.email;
	}

	public void setUsername(String username) {
		this.email = username;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String username) {
		this.email = username;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public Integer getEnabled(){
		return this.enabled;
	}
	
	public Integer isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	
	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getMatchingEmail() {
		return matchingEmail;
	}

	public void setMatchingEmail(String matchingEmail) {
		this.matchingEmail = matchingEmail;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
	

}