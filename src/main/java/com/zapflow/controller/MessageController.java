package com.zapflow.controller;

import com.zapflow.dto.MessageRequest;
import com.zapflow.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Tag(name = "Mensagens", description = "Envio de mensagens via WhatsApp")
@RestController
@RequestMapping("/v1/messages")
@RequiredArgsConstructor
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService messageService;

    @Operation(
            summary = "Envia mensagem via WhatsApp",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Mensagem aceita para envio"),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida")
            }
    )
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody MessageRequest request) {
        log.info("Received sendMessage request: {}", request);
        messageService.send(request);
        return ResponseEntity.accepted().build();
    }
}