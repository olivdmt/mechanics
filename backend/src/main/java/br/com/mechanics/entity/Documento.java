package br.com.mechanics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class Documento {
    @NotBlank(message = "The 'CPF/CNPJ' is required")
    @Column(nullable = false, unique = true, length = 14)
    private String cpfcnpj;


    public Documento(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }


}
