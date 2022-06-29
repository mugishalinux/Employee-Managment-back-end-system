package com.example.EmployeeManagementSystem.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class kafkaConfig {
    @Bean
    public NewTopic testConfigTopic(){
        return TopicBuilder.name("testTopic_12").build();
    }
//    public NewTopic empConfigJsonTopic(){
//        return TopicBuilder.name("employmentTopicJson").build();
//    }
}
