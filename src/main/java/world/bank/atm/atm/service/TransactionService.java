package world.bank.atm.atm.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import world.bank.atm.atm.dto.TransactionDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final KafkaTemplate<String, TransactionDto> kafkaTemplate;

    @Value("${kafka.topics.worldBank.request}")
    private String topicWorldBankRequest;

    public void pay(TransactionDto transactionDto) {
        ProducerRecord<String, TransactionDto> record = new ProducerRecord<>(topicWorldBankRequest, "some-key", transactionDto);
        //TODO try add headers by:
        // 1 - HeaderMapper for  record.headers().add(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString().getBytes());
        // 2 - Message<TransactionDto> m - MessageBuilder.withPayload().addHeader().build();
        record.headers().add(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString().getBytes());
        kafkaTemplate.send(record);
    }
}
