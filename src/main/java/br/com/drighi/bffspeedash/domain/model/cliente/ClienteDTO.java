package br.com.drighi.bffspeedash.domain.model.cliente;

import br.com.drighi.bffspeedash.domain.model.common.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClienteDTO(

        @NotNull(message = "Dados Pessoais nunca devem estar vazios")
        @Valid // Adiciona validação aos objetos internos
        PessoaDTO dadosPessoais,

        @NotNull(message = "Ao menos deve conter um documento")
        @Valid // Adiciona validação nos elementos da lista
        List<DocumentoDTO> listaDocumentos,

        @NotNull(message = "Ao menos deve conter um contato")
        @Valid // Adiciona validação nos elementos da lista
        List<ContatoDTO> listaContatos,

        @NotNull(message = "O endereço não pode estar vazio")
        @Valid // Adiciona validação ao objeto interno
        EnderecoDTO enderecoDTO
) {

    public ClienteDTO {
        if (dadosPessoais == null) {
            throw new IllegalArgumentException("Dados Pessoais nunca devem estar vazios");
        }
        if (listaDocumentos == null || listaDocumentos.isEmpty()) {
            throw new IllegalArgumentException("Ao menos deve conter um documento");
        }
        if (listaContatos == null || listaContatos.isEmpty()) {
            throw new IllegalArgumentException("Ao menos deve conter um contato");
        }
        if (enderecoDTO == null) {
            throw new IllegalArgumentException("O endereço não pode estar vazio");
        }

    }
}