package com.example.concessionaria.config;

import com.example.concessionaria.model.Role;
import com.example.concessionaria.model.Roles;
import lombok.Builder;

import java.util.List;

@Builder
public record JWTUserData(Long userId, String email, List<String> roles) {
}