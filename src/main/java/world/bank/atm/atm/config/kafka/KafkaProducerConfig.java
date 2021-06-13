package world.bank.atm.atm.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.Message;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrapServers}")
    private String kafkaBootstrapServers;

    @Bean
    public ProducerFactory<Object, Message<Object>> producerFactory() {
        Map<String, Object> confProps = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
        );
        return new DefaultKafkaProducerFactory<>(confProps);
    }

    @Bean
    public KafkaTemplate<Object, Message<Object>> kafkaTemplate() {
        return new KafkaTemplate<Object, Message<Object>>(producerFactory());
    }
}
