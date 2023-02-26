package com.dev.controller;

import com.dev.exception.IncorrectPasswordException;
import com.dev.exception.UserNotFoundException;
import com.dev.exception.UsernameExistedException;
import com.dev.service.AuthService;
import com.dev.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final AuthService authService;
    private String loginPage = "login";
    private String registerPage = "register";

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/login")
    public String LoginPage(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) return "redirect:/";

        Boolean hasAdminUser = authService.hasAdminUser();

        if (!hasAdminUser) {
            return "redirect:/" + registerPage;
        }
        model.addAttribute("title", "Login Page");
        return loginPage;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        try {
            User user = authService.login(username, password);
            session.setAttribute("user", user);
            return "redirect:/";
        } catch (IncorrectPasswordException error) {
            model.addAttribute("message", error.getMessage());
        } catch (UserNotFoundException error) {
            model.addAttribute("message", error.getMessage());
        }
        return loginPage;
    }

    @RequestMapping(value = "/register")
    public String RegisterPage(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) return "redirect:/";
        model.addAttribute("title", "Register Page");
        model.addAttribute("hasAdmin", authService.hasAdminUser());
        return registerPage;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String Register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           Model model) {
        Boolean hasUserAdmin = authService.hasAdminUser();
        model.addAttribute("hasAdmin", authService.hasAdminUser());

        try {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("username", username);
                model.addAttribute("message", "password doesn't match");
                return "forward:/" + registerPage;
            }
            if (!hasUserAdmin) {
                authService.registerUserAdmin(username, password);
            } else {
                authService.registerUserReader(username, password);
            }
            return "redirect:/" + loginPage;
        } catch (UsernameExistedException error) {
            model.addAttribute("message", error.getMessage());
        }
        return registerPage;
    }

    @RequestMapping(value = "/logout")
    public String Logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping(value = "/profile")
    public String ProfilePage(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/" + loginPage;
        model.addAttribute("title", "Profile Page");
        return "profile";
    }

    @RequestMapping(value = "/change-password")
    public String ChangePasswordPage(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/" + loginPage;
        model.addAttribute("title", "Change Password Page");
        return "change-password";
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public String ChangePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpSession session,
                                 Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/" + loginPage;

        try {
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("message", "password doesn't match");
            } else {
                authService.changeUserPassword(user.getId(), oldPassword, newPassword);
                model.addAttribute("success",true);
            }
        } catch (IncorrectPasswordException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "change-password";
    }
}
