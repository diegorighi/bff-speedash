package br.com.drighi.bffspeedash.domain.model.pagamento;

import java.util.List;

public record PagamentoDTO(
        List<CartaoCreditoDTO> listaCartoesCredito,
        List<CartaoPrePago> listaCartaoPrePago
) {
}
