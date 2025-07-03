package com.iongroup.dbx.marketplace.test_kafka_consumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iongroup.dbx.marketplace.test_kafka_consumer.model.KafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {
  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "${spring.kafka.incoming-topic}"/*, groupId = "test_consumer_group_1"*/)
//  public void listen(String message) throws JsonProcessingException {
  public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) throws JsonProcessingException {
//    KafkaMessage kafkaMessage = objectMapper.readValue(message, KafkaMessage.class);
    KafkaMessage kafkaMessage = objectMapper.readValue(record.value(), KafkaMessage.class);

    System.out.println("Record key: " + record.key());
    System.out.println("Received Message: " + kafkaMessage.getMessage());
    System.out.println("Headers: " + record.headers().toArray()[0].key() + "; " + new String(record.headers().toArray()[0].value()));

    acknowledgment.acknowledge();
  }
}
