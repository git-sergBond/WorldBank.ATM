package world.bank.atm.atm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import world.bank.atm.atm.dto.MoneyDto;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final KafkaTemplate<Object, Message<String>> kafkaTemplate;

    @Value("${kafka.topics.worldBank.request}")
    private String topicWorldBankRequest;

    public void pay(Long sourceId,Long destinationId, MoneyDto money) {
        String message = "ALLO THIS IS MESSAGE!!!";
        Message<String> msg = MessageBuilder.withPayload(message)
                .setHeader(KafkaHeaders.CORRELATION_ID, 1)
                .setHeader(KafkaHeaders.PARTITION_ID, 2)
                .build();
        ListenableFuture<SendResult<Object, Message<String>>> listenableFuture =  kafkaTemplate.send(topicWorldBankRequest, msg);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Object, Message<String>>>() {
            @Override
            public void onSuccess(SendResult<Object, Message<String>> objectMessageSendResult) {
                System.out.println("success " + objectMessageSendResult.getRecordMetadata().offset() + "|" + objectMessageSendResult.getProducerRecord().toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failure " + throwable.getMessage());
            }
        });
    }
}
