package com.example.qmdzy.qmdzy.controller;

import com.example.qmdzy.qmdzy.model.User;
import com.example.qmdzy.qmdzy.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.example.qmdzy.qmdzy.service.CustomUserDetailsService;
import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/user-center")
    public String userCenter(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("roles", auth.getAuthorities());

        // 获取当前用户昵称
        Optional<User> userOpt = userRepository.findByUsername(username);
        userOpt.ifPresent(user -> model.addAttribute("nickname", user.getNickname()));

        return "user-center";
    }

    @PostMapping("/update-profile")
    public String updateProfile(
            @RequestParam String nickname,
            RedirectAttributes redirectAttributes) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setNickname(nickname);
            userRepository.save(user);

            // 刷新认证信息
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            Authentication newAuth = new UsernamePasswordAuthenticationToken(
                    userDetails, auth.getCredentials(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuth);

            redirectAttributes.addFlashAttribute("successMessage", "昵称更新成功！");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "用户不存在");
        }

        return "redirect:/user-center";
    }

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "用户不存在");
            return "redirect:/user-center";
        }

        User user = userOpt.get();

        // 验证当前密码
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "当前密码错误");
            return "redirect:/user-center";
        }

        // 验证新密码和确认密码
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "新密码和确认密码不一致");
            return "redirect:/user-center";
        }

        // 更新密码
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "密码更新成功！");
        return "redirect:/user-center";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "用户不存在");
            return "redirect:/user-center";
        }

        User user = userOpt.get();

        // 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "密码错误，删除失败");
            return "redirect:/user-center";
        }

        // 删除用户
        userRepository.delete(user);

        // 使当前会话失效
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        redirectAttributes.addFlashAttribute("successMessage", "账户已成功删除");
        return "redirect:/home";
    }
}