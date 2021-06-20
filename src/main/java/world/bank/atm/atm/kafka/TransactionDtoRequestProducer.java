package world.bank.atm.atm.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import world.bank.atm.atm.dto.TransactionDto;

import java.nio.ByteBuffer;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionDtoRequestProducer {

    private final KafkaTemplate<String, TransactionDto> kafkaTemplate;

    @Value("${kafka.topics.worldBank.request}")
    private String requestTopic;

    @Value("${kafka.topics.worldBank.reply.partitionId}")
    private Integer replyPartitionId;

    @Value("${kafka.topics.worldBank.reply}")
    private String replyTopic;

    public void pay(TransactionDto transactionDto) {
        ProducerRecord<String, TransactionDto> record = new ProducerRecord<>(requestTopic, "some-key", transactionDto);
        //TODO try add headers by:
        // 1 - HeaderMapper for  record.headers().add(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString().getBytes());
        // 2 - Message<TransactionDto> m - MessageBuilder.withPayload().addHeader().build();
        record.headers()
                .add(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString().getBytes())//TODO solve this problem with serialisation, and deserialization
                .add(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes())//TODO solve this problem with serialisation, and deserialization
                .add(KafkaHeaders.REPLY_PARTITION, ByteBuffer.allocate(4).putInt(replyPartitionId).array());//TODO solve this problem with serialisation, and deserialization
        kafkaTemplate.send(record).addCallback(System.out::println, System.err::println);
    }
}
