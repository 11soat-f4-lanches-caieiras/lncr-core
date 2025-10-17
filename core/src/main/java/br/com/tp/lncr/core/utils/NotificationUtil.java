package br.com.tp.lncr.core.utils;

public class  NotificationUtil {

    private NotificationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static NotificationData buildNotification(String entity, Integer id, String status) {
        String notificationSufix = null;
        String message = null;
        String prefixMessage = entity + " com id: ";
        switch (status.toUpperCase()) {
            case "RECEIVED":
                notificationSufix = "_ORDER_RECEIVED";
                message = "Novo " + entity.toLowerCase() + " com id: " + id + "recebido.";
                break;
            case "PREPARING":
                notificationSufix = "_ORDER_PREPARING";
                message = prefixMessage + id + " iniciou.";
                break;
            case "READY":
                notificationSufix = "_ORDER_READY";
                message = prefixMessage + id + " pronto.";
                break;
            case "FINISEHD":
                notificationSufix = "_ORDER_FINISHED";
                message = prefixMessage + id + " finalizado.";
                break;
            case "CANCELLED":
                notificationSufix = "_ORDER_CANCELLED";
                message = prefixMessage + id + " cancelado.";
                break;
            default:
                break;
        }
        return new NotificationData(getNotificationType(entity,notificationSufix), id, message);
    }

    public static String getNotificationType(String entity, String sufix) {
        return entity + sufix;
    }

    public record NotificationData(String type, Integer id, String message) {
    }
}
