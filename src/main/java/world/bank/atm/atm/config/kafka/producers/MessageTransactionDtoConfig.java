/*package world.bank.atm.atm.config.kafka.producers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.Message;
import world.bank.atm.atm.dto.TransactionDto;

import java.util.Map;

TODO devide producers for diff dto
@Configuration
public class MessageTransactionDtoConfig {

    @Bean
    public DefaultKafkaProducerFactory<String, Message<TransactionDto>> producerTransactionDtoFactory(
            Map<String, Object> producerConfig) {
        return new DefaultKafkaProducerFactory<>(producerConfig);
    }

    @Bean
    public KafkaTemplate<String, Message<TransactionDto>> kafkaTemplate(
            DefaultKafkaProducerFactory<String, Message<TransactionDto>> producerTransactionDtoFactory) {
        KafkaTemplate<String, Message<TransactionDto>> template = new KafkaTemplate<>(producerTransactionDtoFactory);
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }
}
*/