package com.example.concessionaria.controller;

import com.example.concessionaria.model.Role;
import com.example.concessionaria.model.Roles;
import com.example.concessionaria.model.User;
import com.example.concessionaria.service.RoleService;
import com.example.concessionaria.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PatchMapping("/users/{id}/role")
    public ResponseEntity<Void> changeUserRole(@PathVariable Long id, @RequestParam Roles newRole) {
        if (newRole == null) {
            newRole = Roles.CLIENTE;
        }
        Roles finalNewRole = newRole;
        Role roleEntity = roleService.findByName(newRole)
                .orElseThrow(() -> new RuntimeException("Papel não encontrado: " + finalNewRole));
        userService.alterRole(id, roleEntity);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/users/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/users/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }
}