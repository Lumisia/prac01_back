package com.example.demo.notification;

import com.example.demo.notification.model.NotificationEntity;
import com.example.demo.notification.model.NotificationEntityDto;
import lombok.RequiredArgsConstructor;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final PushService pushService;

    public NotificationService(NotificationRepository notificationRepository) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        this.notificationRepository = notificationRepository;

        if(Security.getProperty(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        this.pushService = new PushService();
        this.pushService.setPublicKey("BF8WcLxvUJtL9lW0ltKSFT0QL5c2Ef4JkaDrpl_ZrNykGSlsAzO68yhe_USgrfBI6KKV1brFpsQ4ZCreQZxd1_0");
        this.pushService.setPrivateKey("4cNVST6P5TNxanwtgQen8x7HvAnKVLnA4TYxistNOKg");
        this.pushService.setSubject("우리 사이트다.");
    }

    public void save(NotificationEntityDto.Subscribe dto) {
        notificationRepository.save(dto.toEntity());
    }

    public void send(NotificationEntityDto.Send dto) throws JoseException, GeneralSecurityException, IOException, ExecutionException, InterruptedException {
        NotificationEntity entity = notificationRepository.findById(dto.getIdx()).orElseThrow();

        Subscription.Keys keys = new Subscription.Keys(
                entity.getP256dh(),
                entity.getAuth()
        );
        Subscription subscription = new Subscription(entity.getEndpoint(), keys);

        Notification notification = new Notification(subscription, NotificationEntityDto.Payload.from(dto).toString());
        pushService.send(notification);

    }
}
