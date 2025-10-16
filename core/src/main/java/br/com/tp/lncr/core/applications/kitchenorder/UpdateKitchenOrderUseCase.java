package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lncr.core.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;
import br.com.tp.lncr.core.utils.NotificationUtil;

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
        NotificationUtil.NotificationData data = NotificationUtil.buildNotification("KITCHEN", kitchenOrder.getId(), kitchenOrder.getStatus());
        if (data.type != null) kitchenOrderGateway.sendNotification(data.type, data.id, data.message);
    }
}
