package br.com.drighi.bffspeedash.domain.model.cliente;

import br.com.drighi.bffspeedash.domain.model.common.EnumTipoDocumento;

public class DocumentoCPF implements Documento {

    private static final String TIPO_DOCUMENTO = EnumTipoDocumento.CPF.toString();

    private String numeroDocumento = null;

    public DocumentoCPF(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }


    @Override
    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public String getTipoDocumento() {
        return TIPO_DOCUMENTO;
    }
}
