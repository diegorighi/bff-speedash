package br.com.drighi.bffspeedash.application.usercase.pagamentos;

import java.math.BigDecimal;

public interface Pagamento {
    public String getId();
    public BigDecimal getValor();
}
