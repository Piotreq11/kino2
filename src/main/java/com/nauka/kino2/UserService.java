package com.nauka.kino2;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return false;
        }

        String hashedPw = passwordEncoder.encode((user.getPassword()));
        user.setPassword(hashedPw);

        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        if(userRole.isPresent())
            user.getRoles().add(userRole.get());

        userRepository.save(user);
        return true;
    }
}
