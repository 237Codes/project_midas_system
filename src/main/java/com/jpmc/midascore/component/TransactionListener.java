package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Transaction transaction) {
        System.out.println("Received Transaction: " + transaction);
        System.out.println("Sender ID: " + transaction.getSenderId());
        System.out.println("Recipient ID: " + transaction.getRecipientId());
        System.out.println("Amount: " + transaction.getAmount());
    }
}
