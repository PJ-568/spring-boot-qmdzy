package com.example.qmdzy.qmdzy.controller;

import com.example.qmdzy.qmdzy.model.User;
import com.example.qmdzy.qmdzy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/login-page")
    public String showLoginForm() {
        return "login-page";
    }

    @PostMapping("/register")
    public String processRegistration(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String nickname,
            Model model) {

        // 检查用户名是否已存在
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }

        // 创建新用户
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setNickname(nickname);
        newUser.setRole(0); // 0表示普通用户

        // 保存用户
        userRepository.save(newUser);

        return "redirect:/login-page?registered";
    }
}