package uk.ac.aston.jonesja1.ers.service.notification;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.request.NotificationRequest;
import uk.ac.aston.jonesja1.ers.model.request.SingleNotificationRequest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Send Notifications to devices enrolled with the system.
 * Uses Firebase Cloud Messaging client to send messages.
 * see https://firebase.google.com/docs/cloud-messaging/
 * for more information.
 */
@Service
public class DeviceNotificationServiceImpl implements DeviceNotificationService {

    Logger logger = LoggerFactory.getLogger(DeviceNotificationServiceImpl.class);

    @Autowired
    private FcmClient fcmClient;

    /**
     * Send a notification to a single device based on their Firebase token.
     * @param notificationRequest request containing the message to send and who the message is for.
     */
    @Override
    public void notifyDevice(SingleNotificationRequest notificationRequest) {
        logger.info("Sending Message to {}", notificationRequest.getConnectionToken());
        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofSeconds(15))
                .build();

        Map<String, String> data = new HashMap<String, String>();
        data.put("MESSAGE", notificationRequest.getMessage());
        fcmClient.send(new DataUnicastMessage(options, notificationRequest.getConnectionToken(), data, null));
        logger.info("Message Sent to {}", notificationRequest.getConnectionToken());
    }

    /**
     * Send a Notification to all devices enrolled with the system.
     * @param notificationRequest request containing the message details to send.
     */
    @Override
    public void notifyAll(NotificationRequest notificationRequest) {
        logger.info("Sending Message to all. Status: {}. Site {}.", notificationRequest.getState().toString(), notificationRequest.getSite());
        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofSeconds(15))
                .build();

        Map<String, String> data = new HashMap<String, String>();
        data.put("STATUS", notificationRequest.getState().toString());
        data.put("SITE", notificationRequest.getSite().toString());
        fcmClient.send(new TopicUnicastMessage(options, new Topic("ERSUpdate"), data, null));
        logger.info("Sent Message to all.");
    }
}
