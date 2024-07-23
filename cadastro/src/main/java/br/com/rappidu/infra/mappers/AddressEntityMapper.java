package br.com.rappidu.infra.mappers;

import br.com.rappidu.domain.entities.Address;
import br.com.rappidu.infra.persistence.entities.AddressEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressEntityMapper {
    AddressEntity toEntity(Address address);
    List<AddressEntity> toEntity(List<Address> addresses);
}
