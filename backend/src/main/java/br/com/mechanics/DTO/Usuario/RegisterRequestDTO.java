package br.com.mechanics.DTO.Usuario;

import br.com.mechanics.entity.Usuario;

public record RegisterRequestDTO(
        String username,
        String email,
        String senha
) {
    public RegisterRequestDTO(Usuario usuario) {
        this(usuario.getUsername(), usuario.getEmail(), usuario.getSenha());
    }
}
