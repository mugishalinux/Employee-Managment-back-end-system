package com.example.EmployeeManagementSystem.kafka;
import com.example.EmployeeManagementSystem.entity.Employment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class EmploymentKafkaProducer {
    private static  final Logger LOGGER =  LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String , Employment> kafkaTemplate;

    public EmploymentKafkaProducer(KafkaTemplate<String, Employment> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
