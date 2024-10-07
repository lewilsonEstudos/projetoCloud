package com.acme.gateway.seguranca;

import com.acme.gateway.service.Jwtutil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuarioAutenticado extends AbstractGatewayFilterFactory<UsuarioAutenticado.Config> {
    private final Jwtutil jwtutil;

    public UsuarioAutenticado(Jwtutil jwtutil) {
        super(Config.class);
        this.jwtutil = jwtutil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            try {

                log.info("Incoming request: {} {}", exchange.getRequest().getMethod().name(), exchange.getRequest().getPath());
                String accessToken = exchange.getRequest().getHeaders()
                        .get(config.getHeaderName()).stream().findFirst().orElseThrow();
                String user = jwtutil.decodeToken(accessToken);
                exchange.getRequest().mutate().header("userID", user).build();
                return chain.filter(exchange);
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    @Getter
    @Setter
    @Builder
    public static class Config {
        private String headerName;
    }
}