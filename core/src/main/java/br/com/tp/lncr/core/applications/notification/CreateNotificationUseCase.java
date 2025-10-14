package br.com.tp.lncr.core.applications.notification;

import br.com.tp.lncr.core.domain.notification.Notification;
import br.com.tp.lncr.core.dtos.notification.NotificationDTO;
import br.com.tp.lncr.core.interfaces.notification.NotificationGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

public class CreateNotificationUseCase {

    private final NotificationGateway notificationGateway;


    public CreateNotificationUseCase(NotificationGateway notificationGateway) {
        this.notificationGateway = notificationGateway;
    }

    public void execute(NotificationDTO notificationDTO) {
        LoggerUtil.info("Iniciando criação de notificação: " + notificationDTO.getMessage());
        Notification notification = new Notification(notificationDTO);
        this.notificationGateway.saveNotification(notification);
        LoggerUtil.info("Notificação criada com sucesso: " + notification.getMessage());
    }
}
