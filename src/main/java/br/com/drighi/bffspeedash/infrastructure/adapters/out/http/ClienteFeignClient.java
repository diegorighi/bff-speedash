package br.com.drighi.bffspeedash.infrastructure.adapters.out.http;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "core-speedash", url = "${core-speedash.url}")
public interface ClienteFeignClient {

    @PostMapping("/api/clientes")
    ClienteDTO salvarCliente(@RequestBody ClienteDTO clienteDTO);

}
