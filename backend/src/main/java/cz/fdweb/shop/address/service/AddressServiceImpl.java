package cz.fdweb.shop.address.service;

import cz.fdweb.shop.address.dto.AddressDTO;
import cz.fdweb.shop.address.dto.AddressSaveDTO;
import cz.fdweb.shop.address.entity.AddressEntity;
import cz.fdweb.shop.address.mapper.AddressMapper;
import cz.fdweb.shop.address.repository.AddressRepository;
import cz.fdweb.shop.advice.BadRequestException;
import cz.fdweb.shop.advice.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;


    /**
     * Konstruktor služby pro práci s adresami.
     *
     * @param addressMapper mapper pro převod mezi DTO a entitou
     * @param addressRepository repozitář pro práci s databází
     */
    public AddressServiceImpl(AddressMapper addressMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    /**
     * Vytvoří nové adresy
     *
     * @param addressSaveDTO vstupní data adresy
     * @return vytvořená adresa jako DTO
     * @throws BadRequestException pokud nejsou splněny validační podmínky
     */
    @Override
    public AddressDTO addAddress(AddressSaveDTO addressSaveDTO) {
        if (addressSaveDTO.getStreet() == null || addressSaveDTO.getStreet().isBlank()) {
            throw new BadRequestException("Street name is required");
        }

        AddressEntity entity = addressMapper.toEntity(addressSaveDTO);
        entity.setId(null);

        entity = addressRepository.save(entity);

        return addressMapper.toDTO(entity);
    }

    public List<AddressDTO> getAllAddresses() {
        List<AddressEntity> entity = addressRepository.findByHidden(false);

        return entity.stream()
                .map( addressMapper::toDTO)
                .toList();
    }

    /**
     * Upraví existující adresu.
     * Původní adresa je označena jako skrytá a vytvoří se nová verze.
     *
     * @param addressId ID adresy
     * @param addressSaveDTO nová data adresy
     * @return upravená adresa
     */
    @Override
    public AddressDTO editAddress(Long addressId, AddressSaveDTO addressSaveDTO) {
        AddressEntity oldEntity = fetchAddressById(addressId);
        AddressEntity newEntity = new AddressEntity();
        addressMapper.cloneEntity(oldEntity, newEntity);

        oldEntity.setHidden(true);
        oldEntity.setHiddenAt(LocalDateTime.now());

        addressMapper.updateEntity(addressSaveDTO, newEntity);

        addressRepository.saveAndFlush(oldEntity);

        AddressEntity saved = addressRepository.save(newEntity);

        return addressMapper.toDTO(saved);
    }

    /**
     * Vrátí adresu podle ID.
     *
     * @param addressId ID adresy
     * @return nalezená adresa
     * @throws NotFoundException pokud adresa neexistuje
     */
    @Override
    public AddressDTO getAddressById(Long addressId) {
        AddressEntity entity = fetchAddressById(addressId);

        return addressMapper.toDTO(entity);
    }

    /**
     * Soft delete adresy (nastaví hidden = true).
     *
     * @param id ID adresy
     * @return upravenou adresu
     */
    @Override
    public AddressDTO removeAddress(Long id) {
        AddressEntity address = fetchAddressById(id);

        address.setHidden(true);
        AddressEntity saved = addressRepository.save(address);

        return addressMapper.toDTO(saved);
    }

    // region: Private methods

    /**
     * Načte adresu podle ID nebo vyhodí výjimku.
     *
     * @param id ID adresy
     * @return entita adresy
     * @throws NotFoundException pokud adresa neexistuje
     */
    private AddressEntity fetchAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address with id " + id + " wasn't found."));
    }

}
