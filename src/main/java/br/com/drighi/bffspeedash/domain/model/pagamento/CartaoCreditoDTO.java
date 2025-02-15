package br.com.drighi.bffspeedash.domain.model.pagamento;

public record CartaoCreditoDTO(
        String numero,
        String validade,
        String CVV,
        EnumBandeiraCartao bandeira
) {
}
