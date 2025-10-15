package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lncr.core.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

public class UpdateKitchenOrderUseCase {

    private final KitchenOrderGateway kitchenOrderGateway;

    public UpdateKitchenOrderUseCase(KitchenOrderGateway kitchenOrderGateway) {
        this.kitchenOrderGateway = kitchenOrderGateway;
    }

    public KitchenOrder updateStatus(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder) {
        String sufixMessage = ", novo status: ";
        LoggerUtil.info("Iniciando atualização de preparo, id: " + kitchenOrderId + sufixMessage + newStatus + ", forçar atualização: " + forceUpdate + ", atualizar pedido do cliente: " + updateCustomerOrder);
        KitchenOrder kitchenOrder = kitchenOrderGateway.getKitchenOrderById(kitchenOrderId);
        if (kitchenOrder != null) {
            kitchenOrder.setStatus(newStatus, forceUpdate);
            kitchenOrder = this.kitchenOrderGateway.saveKitchenOrder(kitchenOrder);
            updateCustomerOrder(kitchenOrder, updateCustomerOrder);
            sendNotification(kitchenOrder);
            LoggerUtil.info("Preparo atualizado com sucesso, id: " + kitchenOrderId + sufixMessage + newStatus);
            return kitchenOrder;
        }
         throw new KitchenOrderException("Não encontrado preparo para o id:" + kitchenOrderId, 404);
    }
    private void updateCustomerOrder(KitchenOrder kitchenOrder, Boolean updateCustomerOrder) {
        LoggerUtil.debug("Atualizando status do pedido do cliente, id: " + kitchenOrder.getCustomerOrderId() + ", novo status: " + kitchenOrder.getStatus() + ", atualizar pedido do cliente: " + updateCustomerOrder);
        if (Boolean.TRUE.equals(updateCustomerOrder) && (kitchenOrder.getStatus().equals(KitchenOrderStatus.PREPARING.getDescription()) ||
                kitchenOrder.getStatus().equals(KitchenOrderStatus.READY.getDescription())))
                   this.kitchenOrderGateway.updateCustomerOrderStatus(kitchenOrder.getCustomerOrderId(), kitchenOrder.getStatus());
    }

    private void sendNotification(KitchenOrder kitchenOrder) {
        if (kitchenOrder == null) return;
        LoggerUtil.debug("Enviando notificação para o preparo, id: " + kitchenOrder.getId() + ", status: " + kitchenOrder.getStatus());
        Integer customerOrderId = kitchenOrder.getId();
        String  notificationType = null;
        String  message = null;
        String prefixMessage = "Preparo com id: ";
        switch (kitchenOrder.getStatus().toUpperCase()) {
            case "PREPARING":
                notificationType = "KITCHEN_ORDER_PREPARING";
                message = prefixMessage + customerOrderId + " iniciado.";
                break;
            case "READY":
                notificationType = "KITCHEN_ORDER_READY";
                message = prefixMessage + customerOrderId + " pronto.";
                break;
            case "FINISEHD":
                notificationType = "KITCHEN_ORDER_FINISHED";
                message = prefixMessage + customerOrderId + " finalizado.";
                break;
            case "CANCELLED":
                notificationType = "KITCHEN_ORDER_CANCELLED";
                message = prefixMessage + customerOrderId + " cancelado.";
                break;
            default:
                break;
        }
        if (notificationType != null)
            this.kitchenOrderGateway.sendNotification(notificationType,customerOrderId,message);
    }
}
