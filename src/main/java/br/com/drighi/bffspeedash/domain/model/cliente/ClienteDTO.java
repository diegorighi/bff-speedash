package br.com.drighi.bffspeedash.domain.model.cliente;

import br.com.drighi.bffspeedash.domain.model.common.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClienteDTO(

        @NotNull(message = "Dados Pessoais nunca devem estar vazios")
        PessoaDTO dadosPessoais,

        @NotNull(message = "Ao menos deve conter um documento")
        List<DocumentoDTO> listaDocumentos,

        @NotNull(message = "Ao menos deve conter um contato")
        List<ContatoDTO> listaContatos,

        @NotNull(message = "O endereço não pode estar vazio")
        EnderecoDTO enderecoDTO
){}
