package br.com.tp.lncr.core.adapters.notification;

import br.com.tp.lncr.core.domain.notification.Notification;
import br.com.tp.lncr.core.dtos.notification.NotificationDTO;

public class NotificationMapper {
    public NotificationDTO notificationToDTO(Notification notification) {
        if (notification == null) return null;
        return new NotificationDTO(
            notification.getId(),
            notification.getNotificationType(),
            notification.getArtefactId(),
            notification.getMessage(),
            notification.getCreated()
        );
    }

    public Notification notificationToDomain(NotificationDTO dto) {
        if (dto == null) return null;
        return new Notification(dto);
    }
}
