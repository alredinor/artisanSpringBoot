package artisanSpringBoot.services.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class ArtisanCustomUserDetails implements UserDetails{

	private Login login;

	public CustomUserDetails(Login login) {
		this.login=login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set <SimpleGrantedAuthority> authorities=new HashSet<>();
		Set <UserRoles> roles=login.getRoles();
		for(UserRoles userRole:roles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
		}
		return authorities;
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return login.isEnable();
	}

}
}
