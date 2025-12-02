package com.example.concessionaria.config;

import com.example.concessionaria.model.Automovel;
import com.example.concessionaria.model.Role;
import com.example.concessionaria.model.User;
import com.example.concessionaria.repository.AutomovelRepository;
import com.example.concessionaria.repository.RoleRepository;
import com.example.concessionaria.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AutomovelRepository automovelRepository;
    private final PasswordEncoder passwordEncoder;

    private final List<String> roleNames = Arrays.asList("ADMIN", "DIRETOR", "VENDEDOR", "CLIENTE");

    public DataLoader(RoleRepository roleRepository,
                      UserRepository userRepository,
                      AutomovelRepository automovelRepository,
                      PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.automovelRepository = automovelRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {

            System.out.println("Iniciando carga de Roles, Users e Automóveis...");

            // --- Criar Roles ---
            for (String roleName : roleNames) {
                if (roleRepository.findByName(roleName).isEmpty()) {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    newRole.setSalario(0.0);
                    roleRepository.save(newRole);
                    System.out.println("Role inserida: " + roleName);
                }
            }

            // --- Criar um usuário por Role ---
            for (String roleName : roleNames) {

                Role role = roleRepository.findByName(roleName).orElseThrow();

                if (!userRepository.existsByRole(role)) {

                    User user = new User();
                    user.setName(roleName.toLowerCase() + "_default");
                    user.setEmail(roleName.toLowerCase() + "@example.com");
                    user.setTelefone("11999999999");
                    user.setEndereco("Endereço padrão");
                    user.setPassword(passwordEncoder.encode("123456"));
                    user.setRole(role);

                    userRepository.save(user);

                    System.out.println("Usuário criado para role: " + roleName);
                }
            }

            // --- Criar automóveis padrões ---
            List<Automovel> carrosIniciais = List.of(
                    new Automovel(null, "Civic 2.0", "EXL", "Honda", 2020, "Prata", "ABC1A23", 115000, true, null),
                    new Automovel(null, "Corolla 1.8", "GLI", "Toyota", 2019, "Preto", "DEF4B56", 105000, true, null),
                    new Automovel(null, "Onix 1.0", "LTZ", "Chevrolet", 2021, "Branco", "GHI7C89", 75000, true, null),
                    new Automovel(null, "HB20 1.6", "Comfort Plus", "Hyundai", 2018, "Azul", "JKL0D12", 62000, true, null)
            );

            for (Automovel auto : carrosIniciais) {
                if (!automovelRepository.existsByPlaca(auto.getPlaca())) {
                    automovelRepository.save(auto);
                    System.out.println("Automóvel inserido: " + auto.getModelo() + " - Placa " + auto.getPlaca());
                }
            }

            System.out.println("Carga inicial concluída.");
        };
    }
}
