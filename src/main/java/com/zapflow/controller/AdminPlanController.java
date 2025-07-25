package com.zapflow.controller;

import com.zapflow.dto.PlanDto;
import com.zapflow.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@Tag(name = "Planos", description = "Gerenciamento de planos de assinatura")
@RestController
@RequestMapping("/v1/admin/plans")
@RequiredArgsConstructor
@Slf4j
@Validated
@SecurityRequirement(name = "bearerAuth")
public class AdminPlanController {

    private final PlanService planService;

    @Operation(
            summary = "Lista todos os planos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de planos recuperada")
            }
    )
    @GetMapping
    public ResponseEntity<List<PlanDto>> getAllPlans() {
        log.info("Fetching all plans");
        return ResponseEntity.ok(planService.getAll());
    }

    @Operation(
            summary = "Recupera um plano por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plano encontrado"),
                    @ApiResponse(responseCode = "404", description = "Plano não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PlanDto> getPlan(@PathVariable Long id) {
        log.info("Fetching plan with id {}", id);
        return ResponseEntity.ok(planService.getById(id));
    }

    @Operation(
            summary = "Cria um novo plano",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plano criado")
            }
    )
    @PostMapping
    public ResponseEntity<PlanDto> createPlan(@Valid @RequestBody PlanDto planDto) {
        log.info("Creating plan: {}", planDto);
        PlanDto created = planService.create(planDto);
        URI location = URI.create("/v1/admin/plans/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @Operation(
            summary = "Atualiza um plano existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plano atualizado"),
                    @ApiResponse(responseCode = "404", description = "Plano não encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<PlanDto> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanDto planDto) {
        log.info("Updating plan id {}: {}", id, planDto);
        return ResponseEntity.ok(planService.update(id, planDto));
    }

    @Operation(
            summary = "Remove um plano",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Plano removido"),
                    @ApiResponse(responseCode = "404", description = "Plano não encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        log.info("Deleting plan with id {}", id);
        planService.delete(id);
        return ResponseEntity.noContent().build();
    }
}