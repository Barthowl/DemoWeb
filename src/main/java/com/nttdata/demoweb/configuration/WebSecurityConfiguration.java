package com.nttdata.demoweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired 
	UserDetailsService servicioUsuario;
	
	String[] resources = new String[] { "/include/**", "/js/**", "/css/**"};
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests() // autorizar peticiones post,get...
	.antMatchers(resources).permitAll() // Se permite el acceso a los 'resources'
	.and().authorizeRequests().antMatchers("/console/**").permitAll() //Permitir acceso a consola de H2
	.and().authorizeRequests().anyRequest().authenticated() // El resto peticiones debe estar autenticadas
	.and().httpBasic()// Permitir autenticación básica para los servicios rest
	.and().formLogin() // Página de login de mi aplicación
	.failureUrl("/login?error=true") // Si hay fallo dónde me direcciona
	.defaultSuccessUrl("/") // Si todo va correcto me manda aquí
	.and().logout().logoutSuccessUrl("/login?logout=true").permitAll()
	.and().rememberMe().key("uniqueAndSecret"); // Para recordar autenticación (!)
	http.csrf().disable();
	http.headers().frameOptions().disable();
	}
	
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(servicioUsuario).passwordEncoder(passwordEncoder());
	}
	
}
