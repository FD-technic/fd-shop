package cz.fdweb.shop.product.mapper;

import cz.fdweb.shop.product.dto.ProductDTO;
import cz.fdweb.shop.product.dto.ProductSaveDTO;
import cz.fdweb.shop.product.entity.ProductEntity;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    ProductEntity toEntity(ProductDTO source);

    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    ProductEntity toEntity(ProductSaveDTO source);

    ProductDTO toDTO(ProductEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "id", ignore = true)
    void cloneEntity(ProductEntity source, @MappingTarget ProductEntity target);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hidden", ignore = true)
    @Mapping(target = "hiddenAt", ignore = true)
    void updateEntity(ProductSaveDTO source, @MappingTarget ProductEntity target);
}