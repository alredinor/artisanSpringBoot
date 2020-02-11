package artisanSpringBoot.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomWebMvcSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
//		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//		http.authorizeRequests().antMatchers("/auth/admin/**").hasAnyRole("ADMIN").
//		and().formLogin().loginPage("/login").
//		and().authorizeRequests().antMatchers("/auth/**").authenticated().
//		and().formLogin().loginPage("/login").permitAll().
//		and().logout().logoutSuccessUrl("/").
//		and().authorizeRequests().antMatchers("/**").permitAll();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
