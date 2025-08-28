package edu.matrix.co.eduapigateway.config;
/**
 * @author Majid.Hussain
 * @type PrePostFilter
 */

import config.Encryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PrePostFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrePostFilter.class);
    private final String ACCESS_TOKEN = "AllowThisRequestAsItIsComingFromGATEWAY";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String accessToken = "";
        try {
            LOGGER.info("IP:" + exchange.getRequest().getRemoteAddress() + " ,URL:" + exchange.getRequest().getURI());
            accessToken = Encryption.encrypt(ACCESS_TOKEN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Add the header to the request before forwarding
        // Mutate request to add custom header
        ServerWebExchange mutatedExchange = exchange.mutate()
            .request(exchange.getRequest().mutate()
                .header("ACCESS-TOKEN", accessToken)
                .build())
            .build();

        // Continue to the next filter in the chain
        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        // Set the filter order to be executed first
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

