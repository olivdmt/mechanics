package br.com.mechanics.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "Perfil")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/pefil")
public class UserController {
    @GetMapping
    public String getPerfil(@AuthenticationPrincipal UserDetails user) {
        return "Usuário Autenticado: " + user.getUsername();
    }
}
