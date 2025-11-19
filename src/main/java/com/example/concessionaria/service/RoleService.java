package com.example.concessionaria.service;

import com.example.concessionaria.model.Role;
import com.example.concessionaria.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<String> findAllRoleNames() {
        return roleRepository.findAllRoleNames();
    }

    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public Optional<Role> findById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public Role save(Role newRole) {
        return roleRepository.save(newRole);
    }

    public Long deleteById(Long id) {
        roleRepository.deleteById(id);
        return id;
    }
}
