package artisanSpringBoot.services.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import artisanSpringBoot.model.Login;
import artisanSpringBoot.model.UserRole;

public class CustomUserDetails implements UserDetails {

	private Login login;

	public CustomUserDetails(Login login) {
		this.login = login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorites = new HashSet<>();
		Set<UserRole> roles = login.getRoles();
		for (UserRole userRole : roles) {
			authorites.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
		}
		return authorites;
	}

	@Override
	public String getPassword() {
		return login.getPassword();
	}

	@Override
	public String getUsername() {
		return login.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return login.isEnable();
	}

}
