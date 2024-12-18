package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private final String topic;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public KafkaProducer(@Value("transactions-topic") String topic, KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String transactionLine) {
        System.out.println("Sending Transaction Line: " + transactionLine); // check if transaction line is being sent from producer
        String[] transactionData = transactionLine.split(", ");

        //separate creation of new transaction so I can log it to verify its creation

        Transaction transactionNew = new Transaction(Long.parseLong(transactionData[0]), Long.parseLong(transactionData[1]), Float.parseFloat(transactionData[2]));
        System.out.println("Sending Transaction: " + transactionNew);

        kafkaTemplate.send(topic, transactionNew);  // Send transaction to topic

    }
}