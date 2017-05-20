package uk.ac.aston.jonesja1.ers.service.notification;

import org.springframework.stereotype.Service;
import uk.ac.aston.jonesja1.ers.model.request.NotificationRequest;
import uk.ac.aston.jonesja1.ers.model.request.SingleNotificationRequest;

@Service
public interface DeviceNotificationService {

    void notifyDevice(SingleNotificationRequest notificationRequest);

    void notifyAll(NotificationRequest notificationRequest);

}
