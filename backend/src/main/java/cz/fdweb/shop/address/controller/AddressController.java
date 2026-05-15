package cz.fdweb.shop.address.controller;

import cz.fdweb.shop.address.dto.AddressDTO;
import cz.fdweb.shop.address.dto.AddressSaveDTO;
import cz.fdweb.shop.address.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public AddressDTO addAddress(@RequestBody AddressSaveDTO addressSaveDTO) {
        return addressService.addAddress(addressSaveDTO);
    }

    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("{productId}")
    public AddressDTO getAddressById(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @PutMapping("/{productId}")
    public AddressDTO editAddress(@PathVariable Long AddressId, @RequestBody AddressSaveDTO AddressSaveDTO) {
        return addressService.editAddress(AddressId, AddressSaveDTO);
    }

    @DeleteMapping("/{productId}")
    public AddressDTO removeAddress(@PathVariable Long addressId) {
        return addressService.removeAddress(addressId);
    }
}
