package eu.credential.wallet.fcm_app_server.api;

import eu.credential.config.FCMClient;
import eu.credential.config.schema.*;
import eu.credential.wallet.notificationservice.ClientProperties;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerThread implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerThread.class);

    private final KafkaConsumer<String, byte[]> consumer;

    KafkaConsumerThread(Properties kafkaProperties, String subscribetopic, String sendtopic) {
        logger.debug("Creating KafkaConsumer");
        this.consumer = new KafkaConsumer<>(kafkaProperties);

        logger.debug("Subscribing to topic: " + subscribetopic);
        this.consumer.subscribe(Arrays.asList(subscribetopic));
    }

    public void run() {

        logger.debug("Running consumer ...");

        while (true) {
            try {

                ConsumerRecords<String, byte[]> records = consumer.poll(100);
                for (ConsumerRecord<String, byte[]> record : records) {

                    // Do decoding stuff similar to the following example:
                    //
                    SpecificDatumReader<FCMNotification> reader = new SpecificDatumReader<>(
                            FCMNotification.getClassSchema());
                    Decoder decoder = DecoderFactory.get().binaryDecoder(record.value(), null);
                    try {
                        FCMNotification fcmNotification = reader.read(null, decoder);

                        logger.info("Received a new FCM Notification {}", fcmNotification);

                        Identifier accountId = fcmNotification.getAccountId();
                        String appId = fcmNotification.getAppId().toString();
                        String notificationId = fcmNotification.getNotificationId().toString();
                        String accountIdValue = accountId.getValue().toString();

                        if (accountId != null) {

                            /*String fcmhost = ClientProperties.getInstance().getValue("fcm.host");
                            String authId = ClientProperties.getInstance().getValue("authId");*/
                            String fcmhost = "fcm.googleapis.com";
                            String authId = "key=AAAADVVgij0:APA91bGTyoC1FZD3ZEpMR0-jdZuGwJ6Pdq0YAMOL7rrf8y-oy2mjTd2Z6M88i3I6EHvTfiQ8APprR8CFj1FO5ruwizqK1Z6pDbkRZ7Isa6IU1Eku8cgHBHW5EK40oYoXcnHXtcsUpz7p";
                            FCMClient fcmClient = new FCMClient(fcmhost, authId);

                            logger.info(
                                    "Create a request for the FCM {}", fcmhost);

                            /*String newMessage = "{\"data\":{\"accountId\":\"" + accountId.getValue().toString() +
                                    "\",\"notificationId\":\"" + notificationId + "\"},\"to\":\"" + appId + ",\"priority\": \"high\"}";*/
                            String newMessage = "{\n" +
                                    "\t\"data\":{\n" +
                                    "\"accountId\":\"" + accountIdValue + "\",\n" +
                                    "\"notificationId\":\"" + notificationId + "\"\n" +
                                    "},\n" +
                                    "\"to\":\"" + appId + "\",\n" +
                                    "\"priority\": \"high\"\n" +
                                    "}";
                            fcmClient.sendNotification(newMessage);
                        }

                    } catch (IOException e) {
                        logger.warn("Could not process received notification event message.", e);
                    }

                }
            } catch (Throwable t) {
                logger.warn("An error occured while processing new data events {}", t);
            }
        }
    }

}