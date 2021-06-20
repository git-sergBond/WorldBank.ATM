package world.bank.atm.atm.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;
import world.bank.atm.atm.dto.TransactionDto;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrapServers}")
    private String kafkaBootstrapServers;

    @Bean
    public Map<String, Object> producerConfig() {
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
                //TODO what meaning: ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId
        );
    }

    @Bean
    public DefaultKafkaProducerFactory<String, TransactionDto> producerTransactionDtoFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, TransactionDto> kafkaTemplate() {
        KafkaTemplate<String, TransactionDto> template = new KafkaTemplate<>(producerTransactionDtoFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public DefaultKafkaHeaderMapper defaultKafkaHeaderMapper() {
        return new DefaultKafkaHeaderMapper();
    }
}
