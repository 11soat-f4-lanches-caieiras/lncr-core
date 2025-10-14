package br.com.tp.lncr.core.adapters.notification;

import br.com.tp.lncr.core.applications.notification.CreateNotificationUseCase;
import br.com.tp.lncr.core.applications.notification.GetNotificationUseCase;
import br.com.tp.lncr.core.domain.notification.Notification;
import br.com.tp.lncr.core.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.interfaces.notification.NotificationController;
import br.com.tp.lncr.core.interfaces.notification.NotificationDatabase;
import br.com.tp.lncr.core.interfaces.notification.NotificationGateway;

import java.util.List;

public class NotificationControllerImpl implements NotificationController {

    private final NotificationGateway notificationGateway;
    private final NotificationMapper notificationMapper;

    public NotificationControllerImpl(NotificationDatabase notificationDatabase) {
        this.notificationMapper = new NotificationMapper();
        this.notificationGateway = new NotificationGatewayImpl(notificationDatabase,this.notificationMapper);
    }

    @Override
    public void createNotification(NotificationDTO notificationDTO) {
        new CreateNotificationUseCase(notificationGateway).execute(notificationDTO);
    }

    @Override
    public List<NotificationDTO> getNotificationByType(String notificationType) {
        List<Notification> notificationsList = new GetNotificationUseCase(notificationGateway).getByType(notificationType);
        return new NotificationPresenter(notificationMapper).getByType(notificationsList);
    }

    @Override
    public List<String> getNotificationTypeList() {
        return new GetNotificationUseCase(notificationGateway).getTypeList();


    }
}
