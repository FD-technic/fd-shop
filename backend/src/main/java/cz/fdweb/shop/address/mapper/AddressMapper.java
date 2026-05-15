package cz.fdweb.shop.address.mapper;

import cz.fdweb.shop.address.dto.AddressDTO;
import cz.fdweb.shop.address.dto.AddressSaveDTO;
import cz.fdweb.shop.address.entity.AddressEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    AddressEntity toEntity(AddressDTO source);

    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    AddressEntity toEntity(AddressSaveDTO source);

    AddressDTO toDTO(AddressEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "id", ignore = true)
    void cloneEntity(AddressEntity source, @MappingTarget AddressEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    void updateEntity(AddressSaveDTO source, @MappingTarget AddressEntity target);
}