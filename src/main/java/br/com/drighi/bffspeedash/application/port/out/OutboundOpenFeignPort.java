package br.com.drighi.bffspeedash.application.port.out;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;

public interface OutboundOpenFeignPort {

    ClienteDTO salvarCliente(ClienteDTO clienteDTO);
}
