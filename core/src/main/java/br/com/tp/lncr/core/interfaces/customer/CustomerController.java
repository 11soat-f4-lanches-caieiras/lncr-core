package br.com.tp.lncr.core.interfaces.customer;

import br.com.tp.lncr.core.dtos.customer.CustomerDTO;

import java.util.List;

public interface CustomerController {

    CustomerDTO create(CustomerDTO customerDto);

    void delete(Integer id);

    List<CustomerDTO> getAll(Integer limit);

    List<CustomerDTO> getByIdList(List<Integer> customerIdList);

    CustomerDTO getByDocumentNumber(String documentNumber);

    CustomerDTO getById(Integer id);

    CustomerDTO partialUpdateById(Integer id, CustomerDTO customerDTO);
}
