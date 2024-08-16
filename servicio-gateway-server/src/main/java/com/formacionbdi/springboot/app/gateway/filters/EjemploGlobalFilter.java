package com.formacionbdi.springboot.app.gateway.filters;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class EjemploGlobalFilter implements GlobalFilter, Ordered {

	private final Logger logger = LoggerFactory.getLogger(EjemploGlobalFilter.class);

	@Override
	@CrossOrigin
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("ejecutando filtro pre");
		exchange.getRequest().mutate().headers(h -> h.add("token", "123456"));

		ServerHttpRequest req = exchange.getRequest();
		ServerWebExchangeUtils.addOriginalRequestUrl(exchange, req.getURI());
		String path = req.getURI().getRawPath();//req.getQueryParams();
		System.out.println("-*-*-*-*-*-*-*-*--*-*-*"+path.toString());
		System.out.println("uri: "+req.getURI().toString());
		System.out.println("Remote Address: "+req.getRemoteAddress().toString());
		System.out.println("Path: "+req.getPath().toString());
		System.out.println("Método: "+req.getMethodValue().toString());
		System.out.println("ID: "+req.getId().toString());
		System.out.println("======================"+req.getQueryParams().toString());

		if (path.equals("/seguridad")) {
			System.out.println("+++++++++++++++++++"+req.getQueryParams().toString());
			
			StringBuffer qpTmp=new StringBuffer("?");
			String qpOut="";
			Map<String, List<String>> qP=req.getQueryParams();
			if (!qP.isEmpty()) {
				qP.forEach((key,value)->{
					String v=value.get(0).toString();
					qpTmp.append("&"+key+"="+v);
				});
				qpOut=qpTmp.toString().replace("?&", "?");
				System.out.println("Parametros: "+qpOut);
			}
			ServerHttpRequest newRequest = req.mutate().path("/listar/search/filtrar"+qpOut).contextPath(null).build();

			exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());

			return chain.filter(exchange.mutate().request(newRequest).build());

		} else {
			System.out.println("--------------------------------");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("ejecutando filtro post");

				Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor -> {
					exchange.getResponse().getHeaders().add("token", valor);
				});

				exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
				// exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);

			}));
		}
		/*
		 * ServerHttpRequest newRequest =
		 * req.mutate().path("http://localhost:8090/listar/").contextPath(null).build();
		 * 
		 * exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR,
		 * newRequest.getURI());
		 * 
		 * return chain.filter(exchange.mutate().request(newRequest).build());
		 */

		/*
		 * return chain.filter(exchange).then(Mono.fromRunnable(() -> {
		 * logger.info("ejecutando filtro post"); ServerHttpRequest req =
		 * exchange.getRequest(); ServerWebExchangeUtils.addOriginalRequestUrl(exchange,
		 * req.getURI()); String path = req.getURI().getRawPath(); String[]
		 * originalParts = StringUtils.tokenizeToStringArray(path, "/");
		 * 
		 * 
		 * System.out.println("*-*--*-*-*-*-* Config: "+exchange.getRequest().getPath().
		 * modifyContextPath("/proto"));
		 * 
		 * Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).
		 * ifPresent(valor -> { exchange.getResponse().getHeaders().add("token", valor);
		 * });
		 * 
		 * exchange.getResponse().getCookies().add("color", ResponseCookie.from("color",
		 * "rojo").build()); //
		 * exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
		 * 
		 * ServerHttpRequest newRequest =
		 * request.mutate().path(newPath.toString()).contextPath(null).build();
		 * 
		 * exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR,
		 * newRequest.getURI());
		 * 
		 * return chain.filter(exchange.mutate().request(newRequest).build()); }));
		 */
	}

	@Override
	public int getOrder() {
		return 10;
	}

}
