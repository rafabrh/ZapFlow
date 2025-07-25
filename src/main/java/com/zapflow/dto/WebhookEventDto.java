package com.zapflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WebhookEventDto(
        @NotBlank String messageId,
        @NotBlank String status,
        @NotNull Long timestamp
) {}