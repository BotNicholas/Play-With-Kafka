package com.iongroup.dbx.marketplace.test_kafka_producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public NewTopic testTopic(@Value("${spring.kafka.outgoing-topic}") String topic) {
    return new NewTopic(topic, 1, (short) 1);
  }
}
