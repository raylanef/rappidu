package br.com.rappidu.infra.controllers.mappers;


import br.com.rappidu.infra.controllers.dto.request.CustomerRequestDto;
import br.com.rappidu.infra.controllers.dto.response.CustomerResponseDto;
import br.com.rappidu.domain.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressModelMapper.class})
public interface CustomerModelMapper {
    Customer toModel(CustomerRequestDto request);
    CustomerResponseDto toResponseDTO(Customer customer);
}
