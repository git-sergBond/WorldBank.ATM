package world.bank.atm.atm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import world.bank.atm.atm.dto.MoneyDto;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topics.worldBank.request}")
    private String topicWorldBankRequest;

    public void pay(Long sourceId,Long destinationId, MoneyDto money) {
        ListenableFuture<SendResult<String, String>> listenableFuture =  kafkaTemplate.send(topicWorldBankRequest,"money from to");
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failure " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.println("success " + stringStringSendResult.getRecordMetadata().offset() + "|" + stringStringSendResult.getProducerRecord().toString());
            }
        });
    }
}
