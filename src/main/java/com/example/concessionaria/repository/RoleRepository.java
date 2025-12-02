package com.example.concessionaria.repository;

import com.example.concessionaria.model.Role;
import com.example.concessionaria.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r.name FROM Role r")
    List<String> findAllRoleNames();

    Optional<Role> findByName(Roles name);
}
