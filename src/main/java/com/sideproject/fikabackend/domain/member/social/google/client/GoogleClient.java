package com.sideproject.fikabackend.domain.member.social.google.client;

import com.sideproject.fikabackend.domain.member.social.google.dto.GoogleAccount;
import com.sideproject.fikabackend.domain.member.social.google.dto.GoogleToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GoogleClient", url = "${api.google.token_base_url}")
public interface GoogleClient {

    @PostMapping("/token")
    GoogleToken getToken(@RequestHeader("Content-Length") String contentLength,
                         @RequestParam("client_id") String clientId,
                         @RequestParam("client_secret") String clientSecret,
                         @RequestParam("code") String code,
                         @RequestParam("redirect_uri") String redirectUri,
                         @RequestParam("grant_type") String grantType);

    @GetMapping("/tokeninfo")
    GoogleAccount getInfo(@RequestParam("id_token") String idToken);

    @PostMapping("/revoke")
    String revokeToken(@RequestParam("token") String token);

}
