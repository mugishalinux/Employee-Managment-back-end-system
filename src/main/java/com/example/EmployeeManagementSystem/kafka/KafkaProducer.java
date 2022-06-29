package com.example.EmployeeManagementSystem.kafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class KafkaProducer {
    private KafkaTemplate<String , String> kafkaTemplate;

    private static  final Logger LOGGER =  LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
//        LOGGER.info(String.format("Message Sent %s" + phoneNumber + " - " + companyName));
        kafkaTemplate.send("testTopic_12" , message);
    }
}
