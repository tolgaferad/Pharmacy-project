package com.pharmacy.demo.controllers;

import com.pharmacy.demo.models.dto.userDTO.*;
import com.pharmacy.demo.models.pojo.User;
import com.pharmacy.demo.services.UserService;
import com.pharmacy.demo.utils.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController extends AbstractController {
    private final UserService userService;
    private final SessionManager sessionManager;

    @GetMapping
    public UserWithoutPasswordDTO getById(HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        User user = userService.getUserById(userId);
        return new UserWithoutPasswordDTO(user);
    }

    @PostMapping
    public UserWithoutPasswordDTO register(@Valid @RequestBody UserRegisterDTO userDTO) {
        User user = userService.addUser(userDTO);
        return new UserWithoutPasswordDTO(user);
    }

    @PostMapping("/login")
    public UserWithoutPasswordDTO login(@Valid @RequestBody UserLoginDTO loginUserDto, HttpSession session) {
        User user = userService.login(loginUserDto);
        sessionManager.loginUser(session, user.getId());
        return new UserWithoutPasswordDTO(user);
    }

    @PutMapping
    public UserWithoutPasswordDTO edit(@Valid @RequestBody UpdateRequestUserDTO editUserDTO,
                                       HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        User user = userService.editUser(editUserDTO, userId);
        return new UserWithoutPasswordDTO(user);
    }

    @DeleteMapping
    public UserWithoutPasswordDTO delete(HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        return userService.deleteUser(userId);
    }

    @GetMapping("/logout")
    public UserWithoutPasswordDTO logout(HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        User user = userService.logoutUser(userId);
        sessionManager.logoutUser(session);
        return new UserWithoutPasswordDTO(user);
    }

    @PostMapping("/change_password")
    public UserWithoutPasswordDTO changePassword(@Valid @RequestBody ChangePassUserDTO changePasswordDTO,
                                                 HttpSession session) {
        int userId = sessionManager.getLoggedId(session);
        User user = userService.changePassword(userId, changePasswordDTO);
        return new UserWithoutPasswordDTO(user);
    }
}
