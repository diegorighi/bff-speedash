package br.com.drighi.bffspeedash.infrastructure.adapters.in.controllers;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.domain.model.cliente.DocumentoCPF;
import br.com.drighi.bffspeedash.infrastructure.adapters.in.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping
    public ResponseEntity<ClienteDTO> buscaClientePorCpf(@RequestBody DocumentoCPF cpf) {
        Optional<ClienteDTO> cliente = this.clienteService.buscaClientePorCPF(cpf.getNumeroDocumento());

        return cliente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
}
