package com.infrastructure.api.mapper;
import com.domain.models.DomainCustomer;
import com.infrastructure.api.dto.CustomerDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomerMapper implements BaseMapper<DomainCustomer, CustomerDTO> {

    @Override
    public CustomerDTO toDto(DomainCustomer entity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(entity.getId());
        customerDTO.setName(entity.getName());
        return customerDTO;
    }




    @Override
    public DomainCustomer toEntity(CustomerDTO dto) {
        DomainCustomer entity = new DomainCustomer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
