package br.com.tp.lncr.core.utils;

import br.com.tp.lncr.core.enums.NotificationStatus;

public class  NotificationUtil {

    private NotificationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static NotificationData buildNotification(String entity, Integer id, String status) {
        NotificationStatus notificationStatus = NotificationStatus.from(status);
        if (notificationStatus == null) {
            return null;
        }
        String message = notificationStatus.formatMessage(entity, id);
        String type = getNotificationType(entity, notificationStatus.getSufix());
        return new NotificationData(type, id, message);
    }

    public static String getNotificationType(String entity, String sufix) {
        return entity + sufix;
    }

    public record NotificationData(String type, Integer id, String message) {
    }
}
