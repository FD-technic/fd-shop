package cz.fdweb.shop.user.mapper;

import cz.fdweb.shop.user.dto.UserDTO;
import cz.fdweb.shop.user.dto.UserSaveDTO;
import cz.fdweb.shop.user.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    UserEntity toEntity(UserDTO source);

    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    UserEntity toEntity(UserSaveDTO source);

    UserDTO toDTO(UserEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "id", ignore = true)
    void cloneEntity(UserEntity source, @MappingTarget UserEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    void updateEntity(UserSaveDTO source, @MappingTarget UserEntity target);
}