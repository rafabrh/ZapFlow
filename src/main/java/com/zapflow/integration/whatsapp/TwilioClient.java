// src/main/java/com/zapflow/integration/whatsapp/TwilioClient.java
package com.zapflow.integration.whatsapp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TwilioClient implements WhatsAppProviderClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${twilio.account-sid}")      private String accountSid;
    @Value("${twilio.auth-token}")       private String authToken;
    @Value("${twilio.base-url}")         private String baseUrl;
    @Value("${twilio.whatsapp-from}")    private String whatsappFrom;

    @Override
    public void sendMessage(String to, String text) {
        MultiValueMap<String,String> form = new LinkedMultiValueMap<>();
        form.add("To",   "whatsapp:" + to);
        form.add("From", whatsappFrom);
        form.add("Body", text);

        webClientBuilder
                .baseUrl(baseUrl)
                .defaultHeaders(h -> h.setBasicAuth(accountSid, authToken))
                .build()
                .post()
                .uri("/Messages.json")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(form)
                .retrieve()
                .bodyToMono(String.class)
                .block();  // PoC síncrono
    }

}
