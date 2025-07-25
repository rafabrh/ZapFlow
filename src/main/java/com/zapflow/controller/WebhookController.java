package com.zapflow.controller;

import com.zapflow.dto.WebhookEventDto;
import com.zapflow.service.WebhookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Tag(name = "Webhook", description = "Recepção de eventos de entrega do WhatsApp")
@RestController
@RequestMapping("/v1/webhook")
@RequiredArgsConstructor
@Slf4j
@Validated
public class WebhookController {

    private final WebhookService webhookService;

    @Operation(
            summary = "Recebe evento de callback de entrega",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evento processado"),
                    @ApiResponse(responseCode = "400", description = "Payload inválido")
            }
    )
    @PostMapping("/receive")
    public ResponseEntity<Void> receiveEvent(@Valid @RequestBody WebhookEventDto eventDto) {
        log.info("Received webhook event: {}", eventDto);
        webhookService.handle(eventDto);
        return ResponseEntity.ok().build();
    }
}
