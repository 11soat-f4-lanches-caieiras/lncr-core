package br.com.tp.lncr.core.applications.customer;

import br.com.tp.lncr.core.domain.customer.Customer;
import br.com.tp.lncr.core.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.exceptions.CustomerException;
import br.com.tp.lncr.core.interfaces.customer.CustomerGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

public class CreateCustomerUseCase {

    public final CustomerGateway customerGateway;

    public CreateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer execute(CustomerDTO customerDTO) {
        LoggerUtil.info("Iniciando criação de cliente: " + customerDTO.getName());
        Customer customer = new Customer(customerDTO);
        validateExistsFields(customer, customerGateway);
        LoggerUtil.info("Finalizando criação de cliente: " + customerDTO.getName());
        return customerGateway.save(customer);
    }

    private void validateExistsFields(Customer customer, CustomerGateway customerGateway) {
        existsByDocumentNumber(customer, customerGateway);
        existsByEmail(customer, customerGateway);
    }

    private void existsByDocumentNumber(Customer customer, CustomerGateway customerGateway) {
        LoggerUtil.debug("Verificando se o cliente já existe pelo número de documento: " + customer.getDocumentNumber());
        if (customerGateway.existsByDocumentNumber(customer.getDocumentNumber())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo número de documento: " + customer.getDocumentNumber(), 409);
        }
    }

    private void existsByEmail(Customer customer, CustomerGateway customerGateway) {
        LoggerUtil.debug("Verificando se o cliente já existe pelo e-mail: " + customer.getEmail());
        if (customerGateway.existsByEmail(customer.getEmail())) {
            throw new CustomerException("Cliente já cadastrado com o mesmo e-mail: " + customer.getEmail(), 409);
        }
    }
}
