package com.formacionbdi.springboot.app.gateway.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

/*Importaciones para CORS*/
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@CrossOrigin
@EnableWebFluxSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@CrossOrigin
	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) {
		return http.authorizeExchange()
				.pathMatchers("/api/security/oauth/**").permitAll()
				.pathMatchers("/api/usuarios/usuarios/**").permitAll()
				.pathMatchers("/api/unidades/unidades/**").permitAll()
				.pathMatchers("/api/operaciones/operaciones/**").permitAll()
				.pathMatchers("/api/objetivos/objetivos/**").permitAll()
				.pathMatchers("/api/balizas/balizas/**").permitAll()
				.anyExchange().authenticated()
				.and().addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				.csrf().disable()
				.build();
		/*.pathMatchers(HttpMethod.GET, "/api/productos/listar",
						"/api/items/listar",
						"/api/usuarios/usuarios/**",
						"/api/unidades/unidades/**",
						"/api/operaciones/operaciones/**",
						"/api/items/ver/{id}/cantidad/{cantidad}",
						"/api/productos/ver/{id}").permitAll()
				.pathMatchers(HttpMethod.POST, "/api/usuarios/usuarios/**",
						"/api/unidades/unidades/**",
						"/api/operaciones/operaciones/**",
						"/api/items/ver/{id}/cantidad/{cantidad}",
						"/api/productos/ver/{id}").permitAll()
				.pathMatchers(HttpMethod.DELETE, "/api/usuarios/usuarios/**",
						"/api/unidades/unidades/**",
						"/api/operaciones/operaciones/**",
						"/api/items/ver/{id}/cantidad/{cantidad}",
						"/api/productos/ver/{id}").permitAll()
				.pathMatchers(HttpMethod.PUT, "/api/usuarios/usuarios/**",
						"/api/unidades/unidades/**",
						"/api/operaciones/operaciones/**",
						"/api/items/ver/{id}/cantidad/{cantidad}",
						"/api/productos/ver/{id}").permitAll()
				.pathMatchers(HttpMethod.PATCH, "/api/usuarios/usuarios/**",
						"/api/unidades/unidades/**",
						"/api/operaciones/operaciones/**",
						"/api/items/ver/{id}/cantidad/{cantidad}",
						"/api/productos/ver/{id}").permitAll()
		 * .pathMatchers(HttpMethod.GET, "/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
				.pathMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/usuarios/**").hasRole("ADMIN")*/
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOrigins(List.of("*"));
	    corsConfig.setMaxAge(3600L);
	    corsConfig.addAllowedMethod("*");
	    corsConfig.addAllowedHeader("*");

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);
	    return source;
	}
	
}
