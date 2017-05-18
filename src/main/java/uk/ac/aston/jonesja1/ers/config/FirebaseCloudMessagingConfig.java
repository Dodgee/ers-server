package uk.ac.aston.jonesja1.ers.config;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.http.options.IFcmClientSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Config for Firebase Cloud Messaging client.
 * Get URL and KEY from external config file.
 */
@Configuration
public class FirebaseCloudMessagingConfig {

    @Autowired
    private IFcmClientSettings config;

    @Bean
    public FcmClient fcmClient() {
        return new FcmClient(config);
    }

    @Component
    public class FirebaseConfig implements IFcmClientSettings {

        @Value("${firebase.cloud.messaging.api.url}")
        private String url;
        @Value("${firebase.cloud.messaging.api.key}")
        private String key;

        @Override
        public String getFcmUrl() {
            return url;
        }

        @Override
        public String getApiKey() {
            return key;
        }
    }
}
