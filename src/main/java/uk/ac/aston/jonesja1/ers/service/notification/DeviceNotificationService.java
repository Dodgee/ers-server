package uk.ac.aston.jonesja1.ers.service.notification;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.http.options.IFcmClientSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DeviceNotificationService {

    public void notifyDevice(String deviceToken) {
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

        NotificationPayload payload = NotificationPayload.builder()
                .setBody("Test Message 1")
                .setTag("Test")
                .setTitle("Test")
                .build();

        client.send(new DataUnicastMessage(options, deviceToken, null, payload));
    }
}
