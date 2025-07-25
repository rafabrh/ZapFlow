package com.zapflow.service;

import com.zapflow.dto.WebhookEventDto;
import com.zapflow.entity.MessageLog;
import com.zapflow.repository.MessageLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhookService {

    private final MessageLogRepository logRepository;

    @Transactional
    public void handle(WebhookEventDto event) {
        MessageLog entry = MessageLog.builder()
                .messageId(event.messageId())
                .event(event.status())
                .timestamp(Instant.ofEpochMilli(event.timestamp()))
                .build();
        logRepository.save(entry);
        log.info("Logged webhook event {} for message {}", event.status(), event.messageId());
    }
}
