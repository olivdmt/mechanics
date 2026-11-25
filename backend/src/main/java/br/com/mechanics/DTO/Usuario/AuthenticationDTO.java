package br.com.mechanics.DTO.Usuario;

import br.com.mechanics.entity.Usuario;

public record AuthenticationDTO(
        String username,
        String senha
) {
    public AuthenticationDTO(Usuario usuario) {
        this(usuario.getUsername(), usuario.getSenha());
    }

}
