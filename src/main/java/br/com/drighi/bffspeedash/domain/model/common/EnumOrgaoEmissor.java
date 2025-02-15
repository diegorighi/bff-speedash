package br.com.drighi.bffspeedash.domain.model.common;

public enum EnumOrgaoEmissor {

    SSP("Secretaria de Segurança Pública"),
    DETRAN("Departamento Estadual de Trânsito"),
    MINISTERIO_DA_FAZENDA("Ministério da Fazenda"),
    CORREIOS("Empresa Brasileira de Correios e Telégrafos"),
    POLICIA_MILITAR("Polícia Militar"),
    IFP("Instituto de Identificação do Paraná"),
    IIRGD("Instituto de Identificação Ricardo Gumbleton Daunt"),
    SES("Secretaria de Saúde"),
    CRBM("Conselho Regional de Biomedicina"),
    CRECI("Conselho Regional de Corretores de Imóveis"),
    OAB("Ordem dos Advogados do Brasil"),
    OUTROS("Outros");

    private final String descricao;

    EnumOrgaoEmissor(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
