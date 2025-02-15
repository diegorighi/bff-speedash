package br.com.drighi.bffspeedash.domain.model.cliente;

public enum EnumSexo {

    M("Masculino"),
    F("Feminino");

    private String sexo;

    EnumSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return this.sexo;
    }
}
