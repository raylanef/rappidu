package br.com.rappidu.infra.controllers.mappers;

import br.com.rappidu.infra.controllers.dto.request.AddressRequestDto;
import br.com.rappidu.domain.entities.Address;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressModelMapper {
    Address toModel(AddressRequestDto request);
    List<Address> toModel(List<AddressRequestDto> request);
}
