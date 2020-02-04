package artisanSpringBoot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login {
	
	@Id
	@Column(name="username", length = 50)
	private String login;
	@Column(name="password", length = 255)
	private String password;
	private boolean enable;
	@OneToMany(mappedBy = "login")
	private Set<UserRole> roles;
	
	public Login() {
		
	}

	public Login(String login, String password, boolean enable, Set<artisanSpringBoot.model.UserRole> roles) {
		super();
		this.login = login;
		this.password = password;
		this.enable = enable;
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	

}
