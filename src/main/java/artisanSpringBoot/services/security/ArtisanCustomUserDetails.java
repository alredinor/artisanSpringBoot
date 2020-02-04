package artisanSpringBoot.services.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import artisanSpringBoot.model.Compte;
import artisanSpringBoot.model.UserRole;



public class ArtisanCustomUserDetails implements UserDetails{

	private Compte compte;

	public ArtisanCustomUserDetails(Compte compte) {
		this.compte=compte;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set <SimpleGrantedAuthority> authorities=new HashSet<>();
		Set <UserRole> roles=compte.getRoles();
		for(UserRole userRole:roles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {

		return compte.getMdp();
	}

	@Override
	public String getUsername() {

		return compte.getLogin();
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
		return compte.isEnable();
	}

}

