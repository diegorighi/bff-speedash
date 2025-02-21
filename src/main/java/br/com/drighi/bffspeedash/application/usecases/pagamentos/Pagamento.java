package br.com.drighi.bffspeedash.application.usecases.pagamentos;

import java.math.BigDecimal;

public interface Pagamento {
    public String getId();
    public BigDecimal getValor();
}
