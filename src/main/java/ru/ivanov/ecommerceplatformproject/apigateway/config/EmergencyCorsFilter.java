package ru.ivanov.ecommerceplatformproject.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class EmergencyCorsFilter {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            var request = ctx.getRequest();
            var response = ctx.getResponse();
            
            // Устанавливаем CORS заголовки для всех ответов
            response.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:8181");
            response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH");
            response.getHeaders().add("Access-Control-Allow-Headers", "*");
            response.getHeaders().add("Access-Control-Allow-Credentials", "true");
            response.getHeaders().add("Access-Control-Max-Age", "3600");
            
            // Если это OPTIONS запрос, сразу возвращаем успех
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
            
            return chain.filter(ctx);
        };
    }
}