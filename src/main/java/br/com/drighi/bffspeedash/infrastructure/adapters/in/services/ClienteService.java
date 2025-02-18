package br.com.drighi.bffspeedash.infrastructure.adapters.in.services;

import br.com.drighi.bffspeedash.application.port.in.InboundClienteHttpRest;
import br.com.drighi.bffspeedash.domain.model.cliente.*;
import br.com.drighi.bffspeedash.domain.model.common.EnderecoDTO;
import br.com.drighi.bffspeedash.domain.model.common.EnumEstado;
import br.com.drighi.bffspeedash.domain.model.common.EnumOrgaoEmissor;
import br.com.drighi.bffspeedash.domain.model.common.EnumTipoDocumento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements InboundClienteHttpRest {
    
    @Override
    public List<ClienteDTO> listaClientes() {
        return List.of();
    }

    @Override
    public Optional<ClienteDTO> buscaClientePorCPF(String cpf) {
        ClienteDTO clienteDTO = new ClienteDTO(
                new PessoaDTO(
                        "João", // primeiroNome
                        "Silva", // nomeDoMeio
                        "Santos", // sobrenome
                        "1990-01-01", // dataNascimento
                        EnumSexo.M // sexo
                ),
                List.of(
                        new DocumentoDTO(
                                EnumTipoDocumento.CPF, // tipoDocumento
                                "123.456.789-00", // numeroDocumento
                                EnumOrgaoEmissor.SSP, // orgaoEmissor
                                EnumEstado.SP, // estadoEmissor
                                "2020-01-01", // dataEmissao
                                "2030-01-01" // dataValidade
                        )
                ),
                List.of(
                        new ContatoDTO(
                                EnumTipoContato.CELULAR, // tipo
                                "(11) 91234-5678" // descritivo
                        )
                ),
                new EnderecoDTO(
                        "Rua das Flores", // logradouro
                        100, // numero
                        "Apto 102", // complemento
                        "Jardim das Palmeiras", // bairro
                        "12345-678", // cep
                        "São Paulo", // cidade
                        EnumEstado.SP// estado
                )
        );

        for(DocumentoDTO numDoc : clienteDTO.listaDocumentos()){
            if(numDoc.numeroDocumento().equals(cpf)){
                return  Optional.of(clienteDTO);
            }else {

                return Optional.empty();
            }
        }
        return null;
    }
}
