package br.com.tp.lncr.core.adapters.notification;

import br.com.tp.lncr.core.domain.notification.Notification;
import br.com.tp.lncr.core.dtos.notification.NotificationDTO;

import java.util.List;

public class NotificationPresenter {

    private final NotificationMapper notificationMapper;

    public NotificationPresenter(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }


    public List<NotificationDTO> getByType(List<Notification> notificationsList) {
        return notificationsList.stream()
                .map(notificationMapper::notificationToDTO)
                .toList();
    }
}
