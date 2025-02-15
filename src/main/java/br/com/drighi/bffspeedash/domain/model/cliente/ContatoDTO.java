package br.com.drighi.bffspeedash.domain.model.cliente;

import jakarta.validation.constraints.NotNull;

public record ContatoDTO(
        @NotNull(message = "Requer o envio de um TIPO DE CONTATO")
        EnumTipoContato tipo,

        @NotNull(message = "Requer o envio de um DESCRITIVO DO CONTATO")
        String descritivo
) {}
