package com.example.concessionaria.controller;

import com.example.concessionaria.config.TokenConfig;
import com.example.concessionaria.dto.request.LoginRequestDTO;
import com.example.concessionaria.dto.request.RegisterUserRequestDTO;
import com.example.concessionaria.dto.response.LoginResponseDTO;
import com.example.concessionaria.dto.response.RegisterUserResponseDTO;
import com.example.concessionaria.model.Role;
import com.example.concessionaria.model.Roles;
import com.example.concessionaria.model.User;
import com.example.concessionaria.repository.RoleRepository;
import com.example.concessionaria.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDTO> register(@Valid @RequestBody RegisterUserRequestDTO request) {
        User user = new User();
        try{
            user = userService.getUserByEmail(request.email());
        }catch (Exception e){
            user = null;
        }
        if(user != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setEmail(request.email());
        newUser.setName(request.name());

        Roles roleName;
        if (request.role() != null) {
            roleName = request.role();
        } else {
            roleName = Roles.CLIENTE;
        }

        Role roleEntity = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Papel não encontrado: " + roleName)); // Ou use uma exceção mais específica

        newUser.setRole(roleEntity);
        userService.saveUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponseDTO(newUser.getName(), newUser.getEmail()));
    }

}












