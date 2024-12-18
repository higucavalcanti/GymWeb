package com.project.gymweb.services;

import com.project.gymweb.dto.create.UserRegisterDTO;
import com.project.gymweb.dto.view.UserRO;
import com.project.gymweb.entities.User;
import com.project.gymweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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
        user.setPassword((user.getPassword()));
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

        return user;
    }

    private UserRO entityToRO(User user) {
        return new UserRO(user.getId(), user.getUsername(), user.getEmail());
    }
}
