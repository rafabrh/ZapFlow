package com.zapflow.service;

import com.zapflow.dto.PlanDto;
import com.zapflow.entity.Plan;
import com.zapflow.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanService {

    private final PlanRepository planRepository;

    public List<PlanDto> getAll() {
        return planRepository.findAll().stream()
                .map(p -> new PlanDto(p.getId(), p.getName(), p.getPrice(), p.getMessageLimit()))
                .collect(Collectors.toList());
    }

    public PlanDto getById(Long id) {
        Plan p = planRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        return new PlanDto(p.getId(), p.getName(), p.getPrice(), p.getMessageLimit());
    }

    @Transactional
    public PlanDto create(PlanDto dto) {
        Plan p = Plan.builder()
                .name(dto.name())
                .price(dto.price())
                .messageLimit(dto.messageLimit())
                .build();
        Plan saved = planRepository.save(p);
        log.info("Created plan {}", saved.getId());
        return new PlanDto(saved.getId(), saved.getName(), saved.getPrice(), saved.getMessageLimit());
    }

    @Transactional
    public PlanDto update(Long id, PlanDto dto) {
        Plan p = planRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        p.setName(dto.name());
        p.setPrice(dto.price());
        p.setMessageLimit(dto.messageLimit());
        Plan saved = planRepository.save(p);
        log.info("Updated plan {}", saved.getId());
        return new PlanDto(saved.getId(), saved.getName(), saved.getPrice(), saved.getMessageLimit());
    }

    public void delete(Long id) {
        planRepository.deleteById(id);
        log.info("Deleted plan {}", id);
    }
}
