package com.yhx.kafka.learn.producer;

import com.yhx.kafka.learn.creator.ProducerCreator;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

/**
 * @author yanghuaxu
 * @date 2021/10/9 11:26
 */
public class ProducerTest {
    private static final String TOPIC = "test-topic";

    public static void main(String[] args) {
        Producer<String, String> producer = ProducerCreator.createProducer();
        ProducerRecord<String, String> record =
                new ProducerRecord<>(TOPIC, "hello, Kafka!");
        try {
            //send message
            RecordMetadata metadata = producer.send(record).get();
            System.out.println("Record sent to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Error in sending record");
            e.printStackTrace();
        }
        producer.close();

    }
}
