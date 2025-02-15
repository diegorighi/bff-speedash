package br.com.drighi.bffspeedash.domain.model.common;

public enum EnumTipoDocumento {
    RG("RG"),
    CPF("CPF"),
    PASSAPORTE("Passaporte"),
    CNH("CNH"),
    OUTROS("Outros");

    private final String descricao;

    EnumTipoDocumento(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
