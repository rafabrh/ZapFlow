// src/main/java/com/zapflow/integration/kafka/OutgoingMessageListener.java
package com.zapflow.integration.kafka;

import com.zapflow.entity.Message;
import com.zapflow.repository.MessageRepository;
import com.zapflow.integration.whatsapp.WhatsAppProviderClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutgoingMessageListener {

    private final WhatsAppProviderClient whatsappClient;
    private final MessageRepository        messageRepository;

    @KafkaListener(
            topics        = "outgoing-messages",
            groupId       = "${spring.kafka.consumer.group-id:zapflow-group}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    public void onMessage(Message msg) {
        log.info("Consumindo do Kafka e enviando WhatsApp: {}", msg);
        // dispara a API do Twilio
        whatsappClient.sendMessage(msg.getDestination(), msg.getText());
        // atualiza status
        msg.setStatus("SENT");
        messageRepository.save(msg);
    }

}
