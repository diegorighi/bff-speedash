package br.com.drighi.bffspeedash.domain.model.common;

import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotNull(message = "Requer o envio de um LOGRADOURO")
        String logradouro,

        Integer numeroLogradouro,

        String complemento,

        @NotNull(message = "Requer o envio de um BAIRRO")
        String bairro,

        @NotNull(message = "Requer o envio de um CEP")
        String cep,

        @NotNull(message = "Requer o envio de uma CIDADE")
        String cidade,

        @NotNull(message = "Requer o envio em um ESTADO")
        EnumEstado estado
) {
}
