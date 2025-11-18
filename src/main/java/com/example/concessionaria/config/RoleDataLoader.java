package com.example.concessionaria.config;

import com.example.concessionaria.model.Role;
import com.example.concessionaria.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RoleDataLoader {

    private final RoleRepository roleRepository;

    // A lista estática de papéis deve ser a mesma usada no SecurityConfig
    private final List<String> roleNames = Arrays.asList(
            "ADMIN", "DIRETOR", "GERENTE_VENDAS", "VENDEDOR",
            "GERENTE_POS_VENDA", "MECANICO", "ATENDENTE_OFICINA",
            "FINANCEIRO", "ESTOQUISTA", "MARKETING", "TI", "CLIENTE"
    );

    public RoleDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    public CommandLineRunner initRoles() {
        return args -> {
            System.out.println("Iniciando verificação e inserção de papéis (Roles)...");

            for (String roleName : roleNames) {
                // Tenta encontrar o papel pelo nome
                if (roleRepository.findByName(roleName).isEmpty()) {

                    // Se não existir, cria e salva a nova entidade Role
                    Role newRole = new Role();
                    newRole.setName(roleName);

                    // Exemplo: Salário padrão de 0.0 para todos, ajuste conforme necessário
                    newRole.setSalario(0.0);

                    roleRepository.save(newRole);
                    System.out.println("Role inserida: " + roleName);
                }
            }
            System.out.println("Verificação e inserção de papéis concluída.");
        };
    }
}