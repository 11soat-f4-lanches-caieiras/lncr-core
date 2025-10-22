package br.com.tp.lncr.core.enums;

public enum NotificationStatus {
    RECEIVED("_ORDER_RECEIVED", "Novo %s com id: %d recebido."),
    PREPARING("_ORDER_PREPARING", "%s com id: %d iniciou."),
    READY("_ORDER_READY", "%s com id: %d pronto."),
    FINISHED("_ORDER_FINISHED", "%s com id: %d finalizado."),
    CANCELLED("_ORDER_CANCELLED", "%s com id: %d cancelado.");

    private final String sufix;
    private final String messageFormat;

    NotificationStatus(String sufix, String messageFormat) {
        this.sufix = sufix;
        this.messageFormat = messageFormat;
    }

    public String getSufix() {
        return sufix;
    }

    public String formatMessage(String entity, Integer id) {
        return String.format(messageFormat, entity, id);
    }

    public static NotificationStatus from(String status) {
        try {
            return NotificationStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}