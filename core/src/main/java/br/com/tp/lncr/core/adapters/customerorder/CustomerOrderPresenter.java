package br.com.tp.lncr.core.adapters.customerorder;

import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import br.com.tp.lncr.core.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.utils.StatusOrderUtils;

import java.util.List;

public class CustomerOrderPresenter {

    private final CustomerOrderMapper customerOrderMapper;

    public CustomerOrderPresenter(CustomerOrderMapper customerOrderMapper) {
        this.customerOrderMapper = customerOrderMapper;
    }

    public CustomerOrderDTO created(CustomerOrder customerOrder) {
        customerOrder.setFoodItems(null);
        return customerOrderMapper.customerOrderToDTO(customerOrder);
    }

    public CustomerOrderDTO getById(CustomerOrder customerOrder) {
        return customerOrderMapper.customerOrderToDTO(customerOrder);
    }

    public List<CustomerOrderDTO> getByStatusList(List<CustomerOrder> customerOrderList, List<String> statusOrderList) {
        return StatusOrderUtils.sortByStatusOrder(customerOrderList, statusOrderList)
                .stream()
                .map(customerOrderMapper::customerOrderToDTO).toList();
    }

    public CustomerOrderDTO updatedStatus(CustomerOrder customerOrder) {
        return this.customerOrderMapper.customerOrderToDTO(customerOrder);
    }
}
