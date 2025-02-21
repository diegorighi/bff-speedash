package br.com.drighi.bffspeedash.infrastructure.adapters.out.http;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.application.port.out.OutboundOpenFeignPort;
import org.springframework.stereotype.Component;

@Component
public class OutboundOpenFeignCliente implements OutboundOpenFeignPort {

    private final ClienteFeignClient clienteFeignClient;

    public OutboundOpenFeignCliente(ClienteFeignClient clienteFeignClient) {
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