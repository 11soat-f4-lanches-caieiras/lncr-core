package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lncr.core.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class GetKitchenOrderUseCase {

    private final KitchenOrderGateway kitchenOrderGateway;

    public GetKitchenOrderUseCase(KitchenOrderGateway kitchenOrderGateway) {
        this.kitchenOrderGateway = kitchenOrderGateway;
    }

    public KitchenOrder getById(Integer kitchenOrderId, Boolean includeFoodItems) {
        LoggerUtil.info("Iniciando busca de preparo pelo id: " + kitchenOrderId);
        KitchenOrder kitchenOrder = this.kitchenOrderGateway.getKitchenOrderById(kitchenOrderId, includeFoodItems);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado preparo para o id: " + kitchenOrderId,404);
        }
        LoggerUtil.info("Preparo encontrado com sucesso, id: " + kitchenOrderId);
        return kitchenOrder;
    }

    public KitchenOrder getByCustomerOrderId(Integer customerOrderId, Boolean includeFoodItems) {
        LoggerUtil.info("Iniciando busca de preparo pelo id do pedido do cliente: " + customerOrderId);
        KitchenOrder kitchenOrder = this.kitchenOrderGateway.getKitchenOrderByCustomerOrderId(customerOrderId, includeFoodItems);
        if (kitchenOrder == null) {
            throw new KitchenOrderException("Não encontrado preparo para o id: " + customerOrderId,404);
        }
        LoggerUtil.info("Preparo encontrado com sucesso, id do pedido do cliente: " + customerOrderId);
        return kitchenOrder;
    }

    public List<KitchenOrder> getByStatusList(List<String> statusList, Boolean includeFoodItems) {
        LoggerUtil.info("Iniciando busca de preparos pelos status: " + statusList);
        List<KitchenOrder> kitchenOrderList = this.kitchenOrderGateway.getKitchenOrderByStatusList(getStatusListIds(statusList), includeFoodItems);
        if (kitchenOrderList == null || kitchenOrderList.isEmpty()) {
            throw new KitchenOrderException("Não encontrado preparo para os status: " + statusList, 404);
        }
        LoggerUtil.info("Preparos encontrados com sucesso, status: " + statusList);
        return kitchenOrderList;
    }

    private List<Integer> getStatusListIds(List<String> statusList){
        LoggerUtil.debug("Convertendo lista de status para IDs: " + statusList);
        List<Integer> statusListIds = new ArrayList<>();
        for(String status : statusList){
            statusListIds.add(KitchenOrderStatus.fromDescription(status).getId());
        }
        LoggerUtil.debug("Lista de IDs de status convertida: " + statusListIds);
        return statusListIds;
    }
}
