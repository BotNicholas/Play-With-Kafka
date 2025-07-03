package com.iongroup.dbx.marketplace.test_kafka_producer.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iongroup.dbx.marketplace.test_kafka_producer.model.KafkaMessage;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestProducer {
  @Autowired
//  private KafkaTemplate<String, KafkaMessage> kafkaTemplate;
  private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private ObjectMapper objectMapper;

  @Value("${spring.kafka.outgoing-topic}")
  private String topic;

  public void sendMessage(KafkaMessage message)
      throws ExecutionException, InterruptedException, JsonProcessingException {
//    SendResult<String, KafkaMessage> result = kafkaTemplate.send("test_topic", message).get();
//    SendResult<String, String> result = kafkaTemplate.send("test_topic", objectMapper.writeValueAsString(message)).get();

//    ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, null, System.currentTimeMillis(), null,
//        objectMapper.writeValueAsString(message),
//        List.of(new Header() {
//          @Override
//          public String key() {
//            return "test-key";
//          }
//
//          @Override
//          public byte[] value() {
//            return "test-value".getBytes();
//          }
//        }));
    ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, null, System.currentTimeMillis(), "TEST_KEY",
        objectMapper.writeValueAsString(message),
        List.of(new RecordHeader("test-key", "test-value".getBytes())));
    SendResult<String, String> result1 = kafkaTemplate.send(producerRecord).get();

    System.out.println(result1);
  }
}
















//package com.iongroup.dbx.marketplace.test_kafka_producer.producers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.iongroup.dbx.marketplace.test_kafka_producer.model.KafkaMessage;
//import java.util.concurrent.ExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TestProducer {
//  @Autowired
//  private KafkaTemplate<String, String> kafkaTemplate;
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @Value("${spring.kafka.outgoing-topic}")
//  private String topic;
//
//  public void sendMessage(KafkaMessage message)
//      throws ExecutionException, InterruptedException, JsonProcessingException {
//    SendResult<String, String> result = kafkaTemplate.send("test_topic", objectMapper.writeValueAsString(message)).get();
//    System.out.println(result);
//  }
//}
