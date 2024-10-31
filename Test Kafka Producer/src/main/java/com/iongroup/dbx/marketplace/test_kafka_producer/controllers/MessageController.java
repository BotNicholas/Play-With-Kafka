package com.iongroup.dbx.marketplace.test_kafka_producer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iongroup.dbx.marketplace.test_kafka_producer.model.KafkaMessage;
import com.iongroup.dbx.marketplace.test_kafka_producer.producers.TestProducer;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
  @Autowired
  TestProducer testProducer;

  @PostMapping("/send")
  @ResponseStatus(HttpStatus.OK)
  public String sendMessage(@RequestBody String message)
      throws ExecutionException, InterruptedException, JsonProcessingException {
    testProducer.sendMessage(new KafkaMessage(message));
    return "Sent";
  }
}
