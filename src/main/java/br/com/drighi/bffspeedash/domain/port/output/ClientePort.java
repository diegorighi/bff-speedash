package br.com.drighi.bffspeedash.domain.port.output;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;

public interface ClientePort {

    ClienteDTO salvarCliente(ClienteDTO clienteDTO);
}
