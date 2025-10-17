package br.com.tp.lncr.core.utils;

public class  NotificationUtil {

    private NotificationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static NotificationData buildNotification(String entity, Integer id, String status) {
        String notificationType = null;
        String message = null;
        String prefixMessage = entity + " com id: ";
        switch (status.toUpperCase()) {
            case "RECEIVED":
                notificationType = entity + "_ORDER_RECEIVED";
                message = "Pagamento finalizado do " + entity.toLowerCase() + " com id: " + id + ". Aguardando preparo.";
                break;
            case "PREPARING":
                notificationType = entity + "_ORDER_PREPARING";
                message = prefixMessage + id + " iniciou.";
                break;
            case "READY":
                notificationType = entity + "_ORDER_READY";
                message = prefixMessage + id + " pronto.";
                break;
            case "FINISEHD":
                notificationType = entity + "_ORDER_FINISHED";
                message = prefixMessage + id + " finalizado.";
                break;
            case "CANCELLED":
                notificationType = entity + "_ORDER_CANCELLED";
                message = prefixMessage + id + " cancelado.";
                break;
            default:
                break;
        }
        return new NotificationData(notificationType, id, message);
    }

    public static class NotificationData {
        public final String type;
        public final Integer id;
        public final String message;
        public NotificationData(String type, Integer id, String message) {
            this.type = type;
            this.id = id;
            this.message = message;
        }
    }
}
