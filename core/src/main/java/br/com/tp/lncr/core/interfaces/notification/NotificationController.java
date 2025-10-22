package br.com.tp.lncr.core.interfaces.notification;

import br.com.tp.lncr.core.dtos.notification.NotificationDTO;

import java.util.List;

public interface NotificationController {

    List<String> getNotificationTypeList();

    List<NotificationDTO> getNotificationByType(String notificationType);

    void createNotification(NotificationDTO notificationDTO);
}
