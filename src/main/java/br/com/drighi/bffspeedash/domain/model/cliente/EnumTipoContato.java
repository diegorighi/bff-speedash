package br.com.drighi.bffspeedash.domain.model.cliente;

public enum EnumTipoContato {

    CELULAR("Celular"),
    TELEFONE_RESIDENCIAL("Telefone Residencial"),
    TELEFONE_RECADO("Telefone Recado"),
    EMAIL("Email");

    private final String descricao;

    EnumTipoContato(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
