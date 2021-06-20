package world.bank.atm.atm.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import world.bank.atm.atm.dto.TransactionDto;

import java.nio.ByteBuffer;

@Service
public class TransactionDtoReplyConsumer {

    @KafkaListener(topics = "${kafka.topics.worldBank.reply}",
            groupId = "${spring.kafka.consumer.group-id}", //TODO make experiment this consumer group
            containerFactory = "singleFactory")
    public void payListener(@Payload TransactionDto message,
                            //TODO add             @Header(KafkaHeaders.MESSAGE_KEY) String messageKey,
                            @Header(KafkaHeaders.CORRELATION_ID) String correlationId
    ) {
    System.out.println("- - - - - - READ - - - - - - - - -");
        // System.out.println("messageKey: " + messageKey);
        System.out.println("correlationId: " + correlationId);//TODO solve this problem with serialisation, and deserialization
        System.out.println("data: " + message);
    }
}
