package com.project.gymweb.services;

import com.project.gymweb.dto.create.UserRegisterDTO;
import com.project.gymweb.dto.view.UserRO;
import com.project.gymweb.entities.User;
import com.project.gymweb.entities.User.Roles;
import com.project.gymweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository repository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserRO> findAll() {
        return repository.findAll().stream().map(this::entityToRO).toList();
    }

    public UserRO findById(UUID id) {
        var user = repository.findById(id).orElseThrow();
        return entityToRO(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }

    public UserRO findByUsernameRO(String username) {
        var user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return entityToRO(user);
    }

    public UserRO findByEmailRO(String email) {
        var user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found"));
        return entityToRO(user);
    }


    public UserRO createUser(UserRegisterDTO userRegisterDTO) {
        var user = dtoToEntity(userRegisterDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var savedUser = repository.save(user);
        return entityToRO(savedUser);
    }

    public UserRO updateUser(UUID id, UserRegisterDTO userRegisterDTO) {
        var user = userRepository.findById(id).orElseThrow();

        user.setUsername(userRegisterDTO.username());
        user.setPassword((userRegisterDTO.password()));
        user.setEmail(userRegisterDTO.email());

        var savedUser = repository.save(user);

        return entityToRO(savedUser);
    }

    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }

    private User dtoToEntity(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(userRegisterDTO.username());
        user.setEmail(userRegisterDTO.email());
        user.setPassword(userRegisterDTO.password());
    
        // Role padrão vai ser "USER", a não ser que seja definido como "ADMIN"
        if (userRegisterDTO.role() != null && userRegisterDTO.role().equalsIgnoreCase(Roles.ADMIN)) {
            user.setRole(Roles.ADMIN);
        } else {
            user.setRole(Roles.USER);
        }
    
        return user;
    }

    private UserRO entityToRO(User user) {
        return new UserRO(user.getId(), user.getUsername(), user.getEmail());
    }

    public void updatePasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!passwordEncoder.matches("test", user.getPassword())) { // Evita criptografar uma senha já codificada
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            }
        }
    }
}
