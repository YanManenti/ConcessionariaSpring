package com.example.concessionaria.service;

import com.example.concessionaria.model.Role;
import com.example.concessionaria.model.User;
import com.example.concessionaria.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User alterRole(Long id, Role newRole) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRole(newRole);
            return userRepository.save(user);
        }
        return null;
    }

    public void deactivateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    public void activateUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setActive(true);
            userRepository.save(user);
        }
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setTelefone(userDetails.getTelefone());
            user.setEndereco(userDetails.getEndereco());
            return userRepository.save(user);
        }
        return null;
    }

}
