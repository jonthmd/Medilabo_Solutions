package com.medilabo.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

/**
 * Configures the routing of requests from the gateway to the microservices.
 */
@Configuration
public class GatewayConfig {

    @Value("${patient.base-url}")
    private String patientBase;

    @Value("${note.base-url}")
    private String noteBase;

    @Value("${risk.base-url}")
    private String riskBase;

    private final RestTemplate restTemplate = new RestTemplate();

    private ServerResponse proxy(ServerRequest request, String targetBase) {
        try {
            String path = request.path()
                    .replaceFirst("/api/patient", "/patient")
                    .replaceFirst("/api/note", "/note")
                    .replaceFirst("/api/risk", "/risk");

            String query = request.uri().getQuery();
            String fullPath = query != null ? path + "?" + query : path;

            RequestEntity<byte[]> requestEntity = RequestEntity
                    .method(request.method(), URI.create(targetBase + fullPath))
                    .headers(headers -> headers.addAll(request.headers().asHttpHeaders()))
                    .body(request.body(byte[].class));

            ResponseEntity<byte[]> response = restTemplate.exchange(requestEntity, byte[].class);

            ServerResponse.BodyBuilder builder = ServerResponse.status(response.getStatusCode())
                    .headers(headers -> headers.addAll(response.getHeaders()));

            byte[] body = response.getBody();
            if (body == null || body.length == 0) {
                return builder.build();
            }

            return builder.body(body);

        } catch (Exception e) {
            return ServerResponse.status(500).body(e.getMessage().getBytes());
        }
    }

    /**
     * Creates the gateway routes.
     *
     * @return The route configuration.
     */
    @Bean
    public RouterFunction<ServerResponse> routes() {

        return RouterFunctions.route()
                .GET("/api/patient/**", req -> proxy(req, patientBase))
                .POST("/api/patient/**", req -> proxy(req, patientBase))
                .PUT("/api/patient/**", req -> proxy(req, patientBase))
                .DELETE("/api/patient/**", req -> proxy(req, patientBase))
                .GET("/api/note/**", req -> proxy(req, noteBase))
                .POST("/api/note/**", req -> proxy(req, noteBase))
                .DELETE("/api/note/**", req -> proxy(req, noteBase))
                .POST("/api/risk/**", req -> proxy(req, riskBase))
                .build();
    }

    //	@Bean
//	public RouterFunction<ServerResponse> testRoute() {
//		return RouterFunctions.route()
//				.GET("/test", request -> ServerResponse.ok().body("Gateway OK !"))
//				.build();
//	}

//    @Bean
//    public RouterFunction<ServerResponse> routes() {
//        return RouterFunctions.route()
//                .GET("/api/patient/**", request ->
//                        ServerResponse.temporaryRedirect(
//                                URI.create("http://localhost:8081" +
//                                        request.path().replace("/api/patient", "/patient"))
//                        ).build()
//                )
//                .POST("/api/patient/**", request ->
//                        ServerResponse.temporaryRedirect(
//                                URI.create("http://localhost:8081" +
//                                        request.path().replace("/api/patient", "/patient"))
//                        ).build()
//                )
//                .PUT("/api/patient/**", request ->
//                        ServerResponse.temporaryRedirect(
//                                URI.create("http://localhost:8081" +
//                                        request.path().replace("/api/patient", "/patient"))
//                        ).build()
//                )
//                .DELETE("/api/patient/**", request ->
//                        ServerResponse.temporaryRedirect(
//                                URI.create("http://localhost:8081" +
//                                        request.path().replace("/api/patient", "/patient"))
//                        ).build()
//                )
//                .GET("/api/note/**", request ->
//                        ServerResponse.temporaryRedirect(
//                                URI.create("http://localhost:8082" +
//                                        request.path().replace("/api/note", "/note"))
//                        ).build()
//                )
//                .POST("/api/note/**", request ->
//                        ServerResponse.temporaryRedirect(
//                                URI.create("http://localhost:8082" +
//                                        request.path().replace("/api/note", "/note"))
//                        ).build()
//                )
//                .PUT("/api/note/**", request ->
//                        ServerResponse.temporaryRedirect(
//                                URI.create("http://localhost:8082" +
//                                        request.path().replace("/api/note", "/note"))
//                        ).build()
//                )
//                .DELETE("/api/note/**", request ->
//                        ServerResponse.temporaryRedirect(
//                                URI.create("http://localhost:8082" +
//                                        request.path().replace("/api/note", "/note"))
//                        ).build()
//                )
//                .build();
//    }
}
