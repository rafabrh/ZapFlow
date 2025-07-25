package com.zapflow.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanDto(
        @Id Long id,
        @NotBlank String name,
        @NotNull Double price,
        @NotNull Integer messageLimit
) {}
