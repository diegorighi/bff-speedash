package br.com.drighi.bffspeedash.domain.model.cliente;

import jakarta.validation.constraints.NotNull;

public record PessoaDTO(
        @NotNull(message = "Requer o envio do primeiroNome")
        String primeiroNome,

        String nomeDoMeio,

        @NotNull(message = "Requer o envio do Sobrenome")
        String sobrenome,

        @NotNull(message = "Requer uma data de nascimento")
        String dataNascimento,

        @NotNull(message = "Sexo indefinido")
        EnumSexo sexo
) {
}
