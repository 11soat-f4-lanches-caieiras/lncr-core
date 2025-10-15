package br.com.tp.lncr.core.interfaces.customer;

import br.com.tp.lncr.core.dtos.customer.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerDatabase {

    void deleteById(Integer id);

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmail(String email);

    List<CustomerDTO> findAll(Integer limit);

    Optional<CustomerDTO> findByDocumentNumber(String documentNumber);

    Optional<CustomerDTO> findById(Integer id);

    List<CustomerDTO> findByIdList(List<Integer> customerIdList);

    CustomerDTO save(CustomerDTO customerDto);
}
