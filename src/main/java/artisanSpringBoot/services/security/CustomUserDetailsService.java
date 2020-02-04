package artisanSpringBoot.services.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import artisanSpringBoot.model.Compte;
import artisanSpringBoot.repositories.CompteRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private CompteRepository compteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Compte> opt =compteRepository.findByIdWithRoles(username);
		if(opt.isPresent()) {
			return new ArtisanCustomUserDetails(opt.get());
		}
		throw new UsernameNotFoundException("not found");
		
	}

}
