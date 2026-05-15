package cz.fdweb.shop.user.controller;

import cz.fdweb.shop.user.dto.UserDTO;
import cz.fdweb.shop.user.dto.UserSaveDTO;
import cz.fdweb.shop.user.filter.UserFilter;
import cz.fdweb.shop.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO addUser(@RequestBody UserSaveDTO userSaveDTO) {
        return userService.addUser(userSaveDTO);
    }

    @GetMapping
    public Page<UserDTO> findUsers(@ModelAttribute UserFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());

        return userService.findUsers(filter, pageable);
    }

    @GetMapping("{productId}")
    public UserDTO getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{productId}")
    public UserDTO editUser(@PathVariable Long userId, @RequestBody UserSaveDTO userSaveDTO) {
        return userService.editUser(userId, userSaveDTO);
    }

    @DeleteMapping("/{productId}")
    public UserDTO removeUser(@PathVariable Long userId) {
        return userService.removeUser(userId);
    }
}
