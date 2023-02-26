package com.dev.service;

import com.dev.exception.IncorrectPasswordException;
import com.dev.exception.UserNotFoundException;
import com.dev.exception.UsernameExistedException;
import com.dev.model.Role;
import com.dev.model.User;
import com.dev.model.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Repository;

@Repository
public class AuthService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public AuthService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User login(String username, String password) throws IncorrectPasswordException, UserNotFoundException {
        User foundUser = userRepository.findUserByUsername(username);
        if (foundUser == null) throw new UserNotFoundException();
        if (!foundUser.isPasswordMatch(password)) throw new IncorrectPasswordException();
        return foundUser;
    }

    private User registerUserByRoleName(String username, String password, String roleName) throws UsernameExistedException {
        User foundUser = userRepository.findUserByUsername(username);
        if (foundUser != null) throw new UsernameExistedException(username);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        Role role = roleService.getRoleByName(roleName);
        user.setRole(role);

        return userRepository.save(user);
    }

    public User registerUserAdmin(String username, String password) throws UsernameExistedException {
        return this.registerUserByRoleName(username, password, "admin");
    }

    public User registerUserReader(String username, String password) throws UsernameExistedException {
        return this.registerUserByRoleName(username, password, "reader");
    }

    public Boolean hasAdminUser() {
        Role adminRole = roleService.getRoleByName("admin");
        int count = userRepository.countUsersByRole(adminRole);
        return count > 0;
    }

    public User changeUserPassword(int userId, String oldPassword, String newPassword) throws IncorrectPasswordException {
        User user = userRepository.findUserById(userId);
        if (!user.isPasswordMatch(oldPassword)) {
            throw new IncorrectPasswordException();
        }
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    public Boolean isUserAdmin(User user) {
        return user.getRole().getName().equals("admin");
    }

    public Boolean isUserReader(User user) {
        return user.getRole().getName().equals("reader");
    }

    public User isUserLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user;
    }
}
