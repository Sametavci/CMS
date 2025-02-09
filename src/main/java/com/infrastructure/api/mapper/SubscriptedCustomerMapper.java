package com.infrastructure.api.mapper;
import com.domain.models.DomainSubscriptedCustomer;
import com.infrastructure.api.dto.SubscriptedCustomerDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component("apiSubscriptedCustomerMapper")
@NoArgsConstructor
public class SubscriptedCustomerMapper implements BaseMapper<DomainSubscriptedCustomer, SubscriptedCustomerDTO> {

    @Override
    public SubscriptedCustomerDTO toDto(DomainSubscriptedCustomer subscriptedCustomerEntity) {
            SubscriptedCustomerDTO subscriptedCustomerDTO = new SubscriptedCustomerDTO();
            subscriptedCustomerDTO.setId(subscriptedCustomerEntity.getId());
            subscriptedCustomerDTO.setAge(subscriptedCustomerEntity.getAge());
            subscriptedCustomerDTO.setMail(subscriptedCustomerEntity.getMail());

            return subscriptedCustomerDTO;
    }

    @Override
    public DomainSubscriptedCustomer toEntity(SubscriptedCustomerDTO subscriptedCustomerDTO) {

        DomainSubscriptedCustomer subscriptedCustomerEntity = new DomainSubscriptedCustomer();

        subscriptedCustomerEntity.setId(subscriptedCustomerDTO.getId());
        subscriptedCustomerEntity.setAge(subscriptedCustomerDTO.getAge());
        subscriptedCustomerEntity.setMail(subscriptedCustomerDTO.getMail());

        return subscriptedCustomerEntity;
    }
}
