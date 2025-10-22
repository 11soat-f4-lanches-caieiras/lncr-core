package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.domain.customer.Customer;
import br.com.tp.lncr.core.exceptions.CustomerException;
import br.com.tp.lncr.core.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

public class DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DeleteCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(Integer id) {
        LoggerUtil.info("Executando a exclusão do cliente com ID: " + id);
        Customer customer = customerGateway.getCustomerById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
        customerGateway.deleteCustomerById(id);
        LoggerUtil.info("Cliente com ID: " + id + " excluído com sucesso.");
    }
}
