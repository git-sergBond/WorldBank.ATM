package world.bank.atm.atm.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import world.bank.atm.atm.dto.MoneyDto;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topics.worldBank.request}")
    private String topicWorldBankRequest;

    public void pay(Long sourceId,Long destinationId, MoneyDto money) {
        kafkaTemplate.send(topicWorldBankRequest,"money from to");
    }
}
