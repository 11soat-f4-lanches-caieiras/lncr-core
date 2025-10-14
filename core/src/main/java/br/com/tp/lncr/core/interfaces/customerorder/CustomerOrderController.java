package br.com.tp.lncr.core.interfaces.customerorder;

import br.com.tp.lncr.core.dtos.customerorder.CustomerOrderDTO;

import java.util.List;

public interface CustomerOrderController {

    CustomerOrderDTO create(CustomerOrderDatabase customerOrderDatabase, CustomerOrderDTO customerOrderDTO);

    CustomerOrderDTO getById(CustomerOrderDatabase customerOrderDatabase, Integer customerOrderId, Boolean includeFoodItems);

    List<CustomerOrderDTO> getByStatusList(CustomerOrderDatabase customerOrderDatabase, List<String> statusList, Boolean includeFoodItems);

    CustomerOrderDTO updateStatusById(CustomerOrderDatabase customerOrderDatabase, Integer customerOrderId, String newStatus, Boolean forceUpdate);

}
