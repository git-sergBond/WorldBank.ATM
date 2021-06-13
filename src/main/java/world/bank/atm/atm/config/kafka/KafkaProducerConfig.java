package world.bank.atm.atm.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.messaging.Message;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrapServers}")
    private String kafkaBootstrapServers;

    @Bean
    public ProducerFactory<Object, Message<String>> producerFactory() {
        Map<String, Object> confProps = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
        );
        return new DefaultKafkaProducerFactory<>(confProps);
    }

    @Bean
    public KafkaTemplate<Object, Message<String>> kafkaTemplate() {
        return new KafkaTemplate<Object, Message<String>>(producerFactory());
    }
}
