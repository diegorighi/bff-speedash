package br.com.drighi.bffspeedash.infrastructure.adapters.http;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.domain.port.output.ClientePort;
import org.springframework.stereotype.Component;

@Component
public class ClienteCoreRestAdapter implements ClientePort {

    private final ClienteFeignClient clienteFeignClient;

    public ClienteCoreRestAdapter(ClienteFeignClient clienteFeignClient) {
        this.clienteFeignClient = clienteFeignClient;
    }

    /**
     * Envia o cliente ao core-speedash pelo OpenFeign.
     */
    @Override
    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        return clienteFeignClient.salvarCliente(clienteDTO);
    }
}