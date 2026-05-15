package cz.fdweb.shop.user.service;

import cz.fdweb.shop.advice.BadRequestException;
import cz.fdweb.shop.advice.NotFoundException;
import cz.fdweb.shop.user.dto.UserDTO;
import cz.fdweb.shop.user.dto.UserSaveDTO;
import cz.fdweb.shop.user.entity.UserEntity;
import cz.fdweb.shop.user.filter.UserFilter;
import cz.fdweb.shop.user.mapper.UserMapper;
import cz.fdweb.shop.user.repository.UserRepository;
import cz.fdweb.shop.user.specification.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    /**
     * Konstruktor služby pro práci s produkty.
     *
     * @param userMapper mapper pro převod mezi DTO a entitou
     * @param userRepository repozitář pro práci s databází
     */
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    /**
     * Vytvoří nového uživatele
     *
     * @param userSaveDTO vstupní data uživatele
     * @return vytvořený uživatel jako DTO
     * @throws BadRequestException pokud nejsou splněny validační podmínky
     */
    @Override
    public UserDTO addUser(UserSaveDTO userSaveDTO) {
        if (userSaveDTO.getName() == null || userSaveDTO.getName().isBlank()) {
            throw new BadRequestException("User name is required");
        }

        UserEntity entity = userMapper.toEntity(userSaveDTO);
        entity.setId(null);

        entity = userRepository.save(entity);

        return userMapper.toDTO(entity);
    }

    /**
     * Vrátí stránkovaný seznam uživatelů podle zadaného filtru.
     *
     * @param filter podmínky filtrování
     * @param pageable nastavení stránkování
     * @return stránka uživatelů
     */
    @Override
    public Page<UserDTO> findUsers(UserFilter filter, Pageable pageable) {

        var spec = UserSpecification.build(filter);

        return userRepository
                .findAll(spec, pageable)
                .map(userMapper::toDTO);
    }

    /**
     * Upraví existujícího uživatele.
     * Původní uživatel je označen jako skrytý a vytvoří se nová verze.
     *
     * @param userId ID uživatele
     * @param userSaveDTO nová data uživatele
     * @return upravený uživatel
     */
    @Override
    public UserDTO editUser(Long userId, UserSaveDTO userSaveDTO) {
        UserEntity oldEntity = fetchUserById(userId);
        UserEntity newEntity = new UserEntity();
        userMapper.cloneEntity(oldEntity, newEntity);

        oldEntity.setHidden(true);
        oldEntity.setHiddenAt(LocalDateTime.now());

        userMapper.updateEntity(userSaveDTO, newEntity);

        userRepository.saveAndFlush(oldEntity);

        UserEntity saved = userRepository.save(newEntity);

        return userMapper.toDTO(saved);
    }

    /**
     * Vrátí uživatele podle ID.
     *
     * @param userId ID uživatele
     * @return nalezený uživatel
     * @throws NotFoundException pokud uživatel neexistuje
     */
    @Override
    public UserDTO getUserById(Long userId) {
        UserEntity entity = fetchUserById(userId);

        return userMapper.toDTO(entity);
    }

    /**
     * Soft delete uživatele (nastaví hidden = true).
     *
     * @param id ID uživatele
     * @return upravený uživatel
     */
    @Override
    public UserDTO removeUser(Long id) {
        UserEntity user = fetchUserById(id);

        user.setHidden(true);
        UserEntity saved = userRepository.save(user);

        return userMapper.toDTO(saved);
    }

    // region: Private methods

    /**
     * Načte uživatele podle ID nebo vyhodí výjimku.
     *
     * @param id ID uživatele
     * @return entita uživatele
     * @throws NotFoundException pokud uživatel neexistuje
     */
    private UserEntity fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " wasn't found."));
    }

}
