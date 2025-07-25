package com.zapflow.dto;

import jakarta.validation.constraints.NotBlank;

public record MessageRequest(
        @NotBlank String tenantId,
        @NotBlank String to,
        @NotBlank String text
) {}