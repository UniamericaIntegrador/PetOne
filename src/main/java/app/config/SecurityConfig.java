package app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;

	@Autowired
	private AuthenticationProvider authenticationProvider;
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http    
		.csrf(AbstractHttpConfigurer::disable)
		.cors(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests((requests) -> requests
				//PERMISSÕES LIVRES
				.requestMatchers("/api/login").permitAll()
				.requestMatchers("/api/login/cadastro").permitAll()
				
				/*
				//PERMISSÕES PACIENTE -- ADMIN, USERVET E USERTUTOR
				.requestMatchers("/api/paciente/listAll").hasAnyRole("ADMIN", "USERVET", "USERTUTOR")
				.requestMatchers("/api/paciente/save").hasAnyRole("ADMIN", "USERTUTOR")
				.requestMatchers("/api/paciente/delete").hasAnyRole("ADMIN", "USERTUTOR")
				.requestMatchers("/api/paciente/update").hasAnyRole("ADMIN", "USERTUTOR")
				
				//PERMISSÕES PROCEDIMENTO -- ADMIN
				.requestMatchers("/api/procedimento/listAll").hasRole("ADMIN")
				.requestMatchers("/api/procedimento/save").hasRole("ADMIN")
				.requestMatchers("/api/procedimento/delete").hasRole("ADMIN")
				.requestMatchers("/api/procedimento/update").hasRole("ADMIN")
				
				/*
				//PERMISSÕES DASHBOARD -- ADMIN
				.requestMatchers("/api/paciente/count").hasRole("ADMIN")
				.requestMatchers("/api/tutor/count").hasRole("ADMIN")
				.requestMatchers("/api/veterinario/count").hasRole("ADMIN")
				.requestMatchers("/api/procedimento/count").hasRole("ADMIN")
				*/
				
				/*
				//PERMISSÕES TUTOR -- ADMIN
				.requestMatchers("/api/tutor/listAll").hasRole("ADMIN")
				.requestMatchers("/api/tutor/save").hasRole("ADMIN")
				.requestMatchers("/api/tutor/delete").hasRole("ADMIN")
				.requestMatchers("/api/tutor/update").hasRole("ADMIN")
				
				//PERMISSÕES VETERINÁRIO -- ADMIN
				.requestMatchers("/api/veterinario/listAll").hasRole("ADMIN")
				.requestMatchers("/api/veterinario/save").hasRole("ADMIN")
				.requestMatchers("/api/veterinario/delete").hasRole("ADMIN")
				.requestMatchers("/api/veterinario/update").hasRole("ADMIN")
				
				//PERMISSÕES ESPÉCIE -- ADMIN
				.requestMatchers("/api/especie/listAll").hasRole("ADMIN")
				.requestMatchers("/api/especie/save").hasRole("ADMIN")
				.requestMatchers("/api/especie/delete").hasRole("ADMIN")
				.requestMatchers("/api/especie/update").hasRole("ADMIN")
				
				//PERMISSÕES RAÇA -- ADMIN
				.requestMatchers("/api/raca/listAll").hasRole("ADMIN")
				.requestMatchers("/api/raca/save").hasRole("ADMIN")
				.requestMatchers("/api/raca/delete").hasRole("ADMIN")
				.requestMatchers("/api/raca/update").hasRole("ADMIN")
				
				//PERMISSÕES ENDEREÇO -- ADMIN
				.requestMatchers("/api/endereco/listAll").hasRole("ADMIN")
				.requestMatchers("/api/endereco/save").hasRole("ADMIN")
				.requestMatchers("/api/endereco/delete").hasRole("ADMIN")
				.requestMatchers("/api/endereco/update").hasRole("ADMIN")
				*/
				
				.anyRequest().authenticated())
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
	
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION,HttpHeaders.CONTENT_TYPE,HttpHeaders.ACCEPT));
		config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),HttpMethod.POST.name(),HttpMethod.PUT.name(),HttpMethod.DELETE.name()));
		config.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(-102);
		return bean;
	}
	

}
