package com.example.qmdzy.qmdzy.init;

import com.example.qmdzy.qmdzy.model.User;
import com.example.qmdzy.qmdzy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // 初始化管理员用户
        if (!userRepository.existsByUsername("202235010623")) {
            User admin = new User();
            admin.setUsername("202235010623");
            admin.setPassword(passwordEncoder.encode("202235010623"));
            admin.setNickname("系统管理员");
            admin.setRole(1); // 1 表示管理员
            userRepository.save(admin);
        }

        // 初始化普通用户
        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setNickname("普通用户");
            user.setRole(0); // 0 表示普通用户
            userRepository.save(user);
        }
    }
}