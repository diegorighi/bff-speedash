package br.com.drighi.bffspeedash.domain.model.pagamento;

public enum EnumBandeiraCartao {
    VISA("Visa"),
    MASTERCARD("MasterCard"),
    AMEX("American Express"),
    ELO("Elo");

    private final String descricao;

    EnumBandeiraCartao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
