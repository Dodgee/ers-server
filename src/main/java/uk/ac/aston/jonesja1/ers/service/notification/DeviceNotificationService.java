package uk.ac.aston.jonesja1.ers.service.notification;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.http.options.IFcmClientSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.request.NotificationRequest;
import uk.ac.aston.jonesja1.ers.model.request.SingleNotificationRequest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeviceNotificationService {
    //TODO refactor FCMClient code
    public void notifyDevice(SingleNotificationRequest notificationRequest) {
        FcmClient client = new FcmClient(new IFcmClientSettings() {
            @Override
            public String getFcmUrl() {
                return "https://fcm.googleapis.com/fcm/send";
            }

            @Override
            public String getApiKey() {
                return "AAAA0wgxdPA:APA91bFS6xVtCNveP74a6APN14WMz1oQVuZxXghK36I9PFrFu3Ga1bxdk7vd6Up2RMXz76Ru-DuXq8wHuU7RalMYUXe7IpHA1iUaSGW9aMGC2FUXGcdroR7et5HynrGscsxHJWEt2ZDN";
            }
        });

        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofSeconds(15))
                .build();

        Map<String, String> data = new HashMap<String, String>();
        data.put("MESSAGE", notificationRequest.getMessage());
        client.send(new DataUnicastMessage(options, notificationRequest.getConnectionToken(), data, null));
    }

    public void notifyAll(NotificationRequest notificationRequest) {
        FcmClient client = new FcmClient(new IFcmClientSettings() {
            @Override
            public String getFcmUrl() {
                return "https://fcm.googleapis.com/fcm/send";
            }

            @Override
            public String getApiKey() {
                return "AAAA0wgxdPA:APA91bFS6xVtCNveP74a6APN14WMz1oQVuZxXghK36I9PFrFu3Ga1bxdk7vd6Up2RMXz76Ru-DuXq8wHuU7RalMYUXe7IpHA1iUaSGW9aMGC2FUXGcdroR7et5HynrGscsxHJWEt2ZDN";
            }
        });

        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofSeconds(15))
                .build();

        Map<String, String> data = new HashMap<String, String>();
        data.put("STATUS", notificationRequest.getState().toString());
        data.put("SITE", notificationRequest.getSite().toString());
        client.send(new TopicUnicastMessage(options, new Topic("ERSUpdate"), data, null));
    }
}
