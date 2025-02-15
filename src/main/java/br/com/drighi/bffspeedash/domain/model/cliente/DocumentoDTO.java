package br.com.drighi.bffspeedash.domain.model.cliente;

import br.com.drighi.bffspeedash.domain.model.common.EnumEstado;
import br.com.drighi.bffspeedash.domain.model.common.EnumOrgaoEmissor;
import br.com.drighi.bffspeedash.domain.model.common.EnumTipoDocumento;
import jakarta.validation.constraints.NotNull;

public record DocumentoDTO(
        @NotNull(message = "Requer o envio de um TIPO DOCUMENTO")
        EnumTipoDocumento tipoDocumento,

        @NotNull(message = "Requer o envio de um NUMERO DE DOCUMENTO")
        String numeroDocumento,

        @NotNull(message = "Requer o envio de um ORGÃO EMISSOR")
        EnumOrgaoEmissor orgaoEmissor,

        @NotNull(message = "Requer o envio de um ESTADO EMISSOR")
        EnumEstado estadoEmissor,

        @NotNull(message = "Requer o envio de uma DATA DE EMISSÃO")
        String dataEmissao,

        @NotNull(message = "Requer o envio de uma DATA DE VALIDADE")
        String dataValidade
) {


}
