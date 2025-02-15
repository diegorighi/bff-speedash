package br.com.drighi.bffspeedash.domain.model.cliente;

import jakarta.validation.constraints.NotNull;

public record CredencialDTO(
        @NotNull(message = "Requer o envio de um LOGIN")
        String login,

        @NotNull(message = "Requer o envio de uma SENHA")
        String senha
) {
}
