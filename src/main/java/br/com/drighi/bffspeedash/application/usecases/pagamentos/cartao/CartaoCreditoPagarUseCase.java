package br.com.drighi.bffspeedash.application.usecases.pagamentos.cartao;

import br.com.drighi.bffspeedash.application.usecases.pagamentos.Pagamento;
import br.com.drighi.bffspeedash.domain.model.pagamento.CartaoCreditoDTO;

import java.math.BigDecimal;

public class CartaoCreditoPagarUseCase implements Pagamento {

    private CartaoCreditoDTO dadosCartao;
    private BigDecimal valor;

    @Override
    public String getId() {
        return "";
    }

    @Override
    public BigDecimal getValor() {
        return null;
    }
}
