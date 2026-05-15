package cz.fdweb.shop.address.repository;

import cz.fdweb.shop.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>, JpaSpecificationExecutor<AddressEntity> {
    List<AddressEntity> findByHidden(boolean hidden);



}
