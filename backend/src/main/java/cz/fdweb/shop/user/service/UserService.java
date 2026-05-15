package cz.fdweb.shop.user.service;

import cz.fdweb.shop.user.dto.UserDTO;
import cz.fdweb.shop.user.dto.UserSaveDTO;
import cz.fdweb.shop.user.filter.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO addUser(UserSaveDTO userSaveDTO);

    Page<UserDTO> findUsers(UserFilter filter, Pageable pageable);

    UserDTO editUser(Long userId, UserSaveDTO userSaveDTO);

    UserDTO getUserById(Long userId);

    UserDTO removeUser(Long id);
}
