package br.com.mechanics.controller;

import br.com.mechanics.DTO.Usuario.AuthenticationDTO;
import br.com.mechanics.DTO.Usuario.LoginResponseDTO;
import br.com.mechanics.DTO.Usuario.RegisterRequestDTO;
import br.com.mechanics.entity.Usuario;
import br.com.mechanics.repository.UsuarioRepository;
import br.com.mechanics.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Autenticação")
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO dto) {
        if (usuarioRepository.findByUsername(dto.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is registered");
        }

        String passwordHash = passwordEncoder.encode(dto.senha());

        Usuario newUser = new Usuario();
        newUser.setUsername(dto.username());
        newUser.setEmail(dto.email());
        newUser.setSenha(passwordHash);
        newUser.setRole("USER");

        usuarioRepository.save(newUser);

        return ResponseEntity.status(201).body("User was registered with successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO dto) {
        var userOpt = usuarioRepository.findByUsername(dto.username());
        if (userOpt.isPresent()) {
            var user = userOpt.get();

            if (passwordEncoder.matches(dto.senha(), user.getSenha())) {
                String token = tokenService.generateToken(user);
                return ResponseEntity.ok(new LoginResponseDTO(token));
            }
        }
        return  ResponseEntity.status(401).body("User or password invalid");
    }
}
