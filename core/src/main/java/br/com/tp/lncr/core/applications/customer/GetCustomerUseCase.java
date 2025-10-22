package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.domain.customer.Customer;
import br.com.tp.lncr.core.exceptions.CustomerException;
import br.com.tp.lncr.core.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

import java.util.List;

public class GetCustomerUseCase {

    private final CustomerGateway customerGateway;

    public GetCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer getById(Integer id) {
        LoggerUtil.info("Buscando cliente com ID: " + id);
        Customer customer = customerGateway.getCustomerById(id);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o ID: " + id, 404);
        }
        LoggerUtil.info("Cliente encontrado: " + customer);
        return customer;
    }

    public List<Customer> getAll(Integer limit) {
        if ((limit <= 0 || limit > 50)) {
            throw new CustomerException("Limite deve ser maior que 0 e menor ou igual a 50", 400);
        }
        limit = limit == null ? 10 : limit;
        return customerGateway.getAllCustomers(limit);
    }


    public Customer getByDocumentNumber(String documentNumber) {
        LoggerUtil.info("Buscando cliente com número de documento: " + documentNumber);
        Customer customer = customerGateway.getCustomerByDocumentNumber(documentNumber);
        if (customer == null) {
            throw new CustomerException("Cliente não encontrado com o número de documento: " + documentNumber, 404);
        }
        LoggerUtil.info("Cliente encontrado: " + customer);
        return customer;
    }

    public List<Customer> getByIdList(List<Integer> customerIdList) {
        LoggerUtil.info("Buscando clientes com IDs: " + customerIdList);
        List<Customer> customer = customerGateway.getCustomerByIdList(customerIdList);
        if (customer == null){
            throw new CustomerException("Não foram encontrados clientes com os id: " + customerIdList, 404);
        }
        LoggerUtil.info("Clientes encontrados: " + customer);
        return customer;
    }
}
