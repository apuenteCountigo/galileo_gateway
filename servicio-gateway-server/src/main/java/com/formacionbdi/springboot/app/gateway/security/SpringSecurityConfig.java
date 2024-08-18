package com.formacionbdi.springboot.app.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebFluxSecurity
public class SpringSecurityConfig {

	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) {
		return http.cors().configurationSource(request -> {
			CorsConfiguration cc = new CorsConfiguration();
			cc.applyPermitDefaultValues();
			cc.addAllowedMethod(HttpMethod.DELETE);
			cc.addAllowedMethod(HttpMethod.PATCH);
			cc.addAllowedMethod(HttpMethod.OPTIONS);
			cc.addAllowedMethod(HttpMethod.PUT);
			return cc;
		}).and().csrf().disable().authorizeExchange()
				.pathMatchers("/api/security/oauth/**", "/api/usuarios/usuarios/search/buscarTip").permitAll()
				.pathMatchers(HttpMethod.OPTIONS).permitAll()
				.pathMatchers(HttpMethod.GET,
						"/api/usuarios/usuarios/search/**",
						"/api/trazabilidad/listar/search/**",
						"/api/histobjbal/listar/search/**")
				.hasAnyAuthority("Administrador de Unidad", "Super Administrador")
				.pathMatchers(HttpMethod.GET,
						"/api/unidades/unidades/search/**",
						"/api/unidades-usuarios/listar/search/**",
						"/api/balizas/balizas/search/**",
						"/api/permisos/listar/search/**",
						"/api/operaciones/operaciones/search/**",
						"/api/evidencias/**",
						"/api/objetivos/listar/search/**",
						"/api/geocercas/**")
				.hasAnyAuthority("Administrador de Unidad", "Usuario Final", "Invitado Externo", "Super Administrador")
				.pathMatchers(HttpMethod.GET,
						"/api/usuarios/**",
						"/api/unidades/**",
						"/api/unidades-usuarios/**",
						"/api/balizas/**",
						"/api/permisos/**",
						"/api/operaciones/**",
						"/api/objetivos/**",
						"/api/tiposcontratos/**",
						"/api/histobjbal/**")
				.hasAuthority("Super Administrador")
				.pathMatchers(HttpMethod.GET,
						"/api/empleos/**",
						"/api/juzgados/**",
						"/api/modelosbalizas/**",
						"/api/estados/**",
						"/api/perfiles/**",
						"/api/importador/**",
						"/api/provincias/**",
						"/api/conexiones/**",
						"/api/apis/**",
						"/api/tiposcontratos/**",
						"/api/tipobalizas/**")
				.permitAll()

				/* POST */
				.pathMatchers(HttpMethod.POST,
						"/api/usuarios/usuarios/**",
						"/api/unidades/unidades/**",
						"/api/unidades-usuarios/**",
						"/api/balizas/**",
						"/api/empleos/**",
						"/api/juzgados/**",
						"/api/modelosbalizas/**",
						"/api/estados/**",
						"/api/perfiles/**",
						"/api/importador/**",
						"/api/provincias/**",
						"/api/conexiones/**",
						"/api/tipobalizas/**",
						"/api/permisos/**",
						"/api/operaciones/operaciones/**",
						"/api/objetivos/**",
						"/api/tiposcontratos/**",
						"/api/trazabilidad/**",
						"/api/histobjbal/**")
				.hasAnyAuthority("Super Administrador", "Administrador de Unidad")

				.pathMatchers(HttpMethod.PUT,
						"/api/usuarios/usuarios/**",
						"/api/unidades/unidades/**",
						"/api/unidades-usuarios/**",
						"/api/balizas/**",
						"/api/empleos/**",
						"/api/juzgados/**",
						"/api/modelosbalizas/**",
						"/api/estados/**",
						"/api/perfiles/**",
						"/api/importador/**",
						"/api/provincias/**",
						"/api/conexiones/**",
						"/api/tipobalizas/**",
						"/api/permisos/**",
						"/api/operaciones/operaciones/**",
						"/api/objetivos/**",
						"/api/tiposcontratos/**",
						"/api/trazabilidad/**",
						"/api/histobjbal/**")
				.hasAnyAuthority("Super Administrador", "Administrador de Unidad")

				.pathMatchers(HttpMethod.PUT,
						"/api/evidencias/**",
						"/api/apis/**",
						"/api/geocercas/**")
				.hasAnyAuthority("Super Administrador", "Administrador de Unidad", "Usuario Final", "Invitado Externo")
				.pathMatchers(HttpMethod.DELETE,
						"/api/geocercas/**")
				.hasAnyAuthority("Super Administrador", "Administrador de Unidad", "Usuario Final", "Invitado Externo")

				.pathMatchers(HttpMethod.POST,
						"/api/evidencias/**",
						"/api/apis/**",
						"/api/geocercas/**")
				.hasAnyAuthority("Super Administrador", "Administrador de Unidad", "Usuario Final", "Invitado Externo")

				.pathMatchers(HttpMethod.PATCH,
						"/api/unidades/unidades/**",
						"/api/unidades-usuarios/**",
						"/api/empleos/**",
						"/api/juzgados/**",
						"/api/modelosbalizas/**",
						"/api/estados/**",
						"/api/perfiles/**",
						"/api/importador/**",
						"/api/provincias/**",
						"/api/conexiones/**",
						"/api/tipobalizas/**",
						"/api/permisos/**",
						"/api/operaciones/operaciones/**",
						"/api/objetivos/**",
						"/api/tiposcontratos/**",
						"/api/trazabilidad/**",
						"/api/histobjbal/**")
				.hasAnyAuthority("Super Administrador", "Administrador de Unidad")
				.pathMatchers(HttpMethod.PATCH,
						"/api/balizas/**",
						"/api/apis/**",
						"/api/usuarios/usuarios/**")
				.hasAnyAuthority("Super Administrador", "Administrador de Unidad", "Usuario Final", "Invitado Externo")

				.pathMatchers(HttpMethod.DELETE,
						"/api/usuarios/usuarios/**",
						"/api/unidades/unidades/**",
						"/api/unidades-usuarios/**",
						"/api/balizas/**",
						"/api/empleos/**",
						"/api/juzgados/**",
						"/api/modelosbalizas/**",
						"/api/estados/**",
						"/api/perfiles/**",
						"/api/importador/**",
						"/api/provincias/**",
						"/api/conexiones/**",
						"/api/apis/**",
						"/api/tipobalizas/**",
						"/api/permisos/**",
						"/api/operaciones/operaciones/**",
						"/api/objetivos/**",
						"/api/tiposcontratos/**",
						"/api/trazabilidad/**",
						"/api/trazabilidad/**",
						"/api/histobjbal/**")
				.hasAnyAuthority("Super Administrador", "Administrador de Unidad")
				.anyExchange()
				.authenticated().and().addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
				.build();
	}

}
