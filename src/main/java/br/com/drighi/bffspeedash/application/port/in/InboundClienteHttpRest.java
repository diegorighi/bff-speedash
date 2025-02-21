package br.com.drighi.bffspeedash.application.port.in;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.domain.model.cliente.Documento;

import java.util.List;
import java.util.Optional;

public interface InboundClienteHttpRest {

    List<ClienteDTO> listaClientes();
    Optional<ClienteDTO> buscaClientePorCPF(String cpf);
}
