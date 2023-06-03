package com.sideproject.fikabackend.domain.social.google.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleAccountsClient", url = "https://accounts.google.com")
public interface GoogleAccountsClient {

    @GetMapping("/o/oauth2/v2/auth")
    String getAuthCode(@RequestParam("client_id") String clientId,
                       @RequestParam("redirect_uri") String redirectUri,
                       @RequestParam("response_type") String responseType,
                       @RequestParam("scope") String scope,
                       @RequestParam("access_type") String accessType,
                       @RequestParam("prompt") String prompt);
}
