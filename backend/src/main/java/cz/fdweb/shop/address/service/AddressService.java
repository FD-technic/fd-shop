package cz.fdweb.shop.address.service;

import cz.fdweb.shop.address.dto.AddressDTO;
import cz.fdweb.shop.address.dto.AddressSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    AddressDTO addAddress(AddressSaveDTO addressSaveDTO);

    List<AddressDTO> getAllAddresses();

    AddressDTO editAddress(Long userId, AddressSaveDTO addressSaveDTO);

    AddressDTO getAddressById(Long userId);

    AddressDTO removeAddress(Long id);
}
