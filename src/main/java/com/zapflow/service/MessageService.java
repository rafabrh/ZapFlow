package com.zapflow.service;

import com.zapflow.dto.MessageRequest;
import com.zapflow.entity.Message;
import com.zapflow.integration.whatsapp.WhatsAppProviderClient;
import com.zapflow.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final WhatsAppProviderClient whatsappClient;

    @Transactional
    public void send(MessageRequest request) {
        Message msg = Message.builder()
                .tenantId(request.tenantId())
                .destination(request.to())
                .text(request.text())
                .status("PENDING")
                .build();
        messageRepository.save(msg);
        kafkaTemplate.send("outgoing-messages", msg);
        log.info("Published message {} to Kafka topic", msg.getId());
    }
}