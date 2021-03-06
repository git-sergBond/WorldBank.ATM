package world.bank.atm.atm.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import world.bank.atm.atm.constants.KafkaProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${"+ KafkaProperties.BOOTSTRAP_SERVERS +"}")
    private String kafkaBootstrapServers;

    @Value("${kafka.topics.worldBank.reply}")
    private String topicWorldBankResponse;

    @Value("${kafka.topics.worldBank.callback}")
    private String topicWorldBankCallback;

    @Bean
    KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        return new KafkaAdmin(config);
    }

    @Bean
    NewTopic atmCallbackTopic() {
        return new NewTopic(topicWorldBankCallback, 1, (short)1);
    }

    @Bean
    NewTopic atmResponseTopic() {
        return new NewTopic(topicWorldBankResponse, 1, (short)1);
    }
}
