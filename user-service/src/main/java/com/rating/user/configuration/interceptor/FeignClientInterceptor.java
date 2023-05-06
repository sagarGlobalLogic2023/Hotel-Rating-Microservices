package com.rating.user.configuration.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private final OAuth2AuthorizedClientManager manager;

    @Autowired
    public FeignClientInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    @Override
    public void apply(RequestTemplate template) {
        String token = Objects.requireNonNull(manager.authorize(OAuth2AuthorizeRequest
                .withClientRegistrationId("my-internal-client")
                .principal("internal")
                .build())).getAccessToken().getTokenValue();

        template.header("Authorization", "Bearer " + token);

    }
}
