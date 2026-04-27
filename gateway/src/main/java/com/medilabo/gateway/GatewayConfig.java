package com.medilabo.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Configuration
public class GatewayConfig {

    //	@Bean
//	public RouterFunction<ServerResponse> testRoute() {
//		return RouterFunctions.route()
//				.GET("/test", request -> ServerResponse.ok().body("Gateway OK !"))
//				.build();
//	}

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .GET("/api/patient/**", request ->
                        ServerResponse.temporaryRedirect(
                                URI.create("http://localhost:8081" +
                                        request.path().replace("/api/patient", "/patient"))
                        ).build()
                )
                .GET("/api/note/**", request ->
                        ServerResponse.temporaryRedirect(
                                URI.create("http://localhost:8082" +
                                        request.path().replace("/api/note", "/note"))
                        ).build()
                )
                .build();
    }
}
