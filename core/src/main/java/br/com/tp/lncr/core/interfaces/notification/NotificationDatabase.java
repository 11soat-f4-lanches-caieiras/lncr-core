package br.com.tp.lncr.core.interfaces.notification;

import br.com.tp.lncr.core.dtos.notification.NotificationDTO;

import java.util.List;

public interface NotificationDatabase {

    List<NotificationDTO> findByNotificationType(String artefactType);

    void save(NotificationDTO notificationDTO);

    List<String> findNotificationTypeList();


}
