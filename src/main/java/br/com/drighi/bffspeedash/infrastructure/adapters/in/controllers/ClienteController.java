package br.com.drighi.bffspeedash.infrastructure.adapters.in.controllers;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.infrastructure.adapters.in.services.ClienteService;
import br.com.drighi.bffspeedash.infrastructure.adapters.in.services.REDISClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final REDISClienteService clienteRedisService;
    public ClienteService clienteService;

    public ClienteController(
            REDISClienteService clienteRedisService,
            ClienteService clienteService
    ) {
        this.clienteRedisService = clienteRedisService;
        this.clienteService = clienteService;
    }

    @PostMapping("salvar-no-redis")
    public void salvarClienteREDIS(@RequestBody ClienteDTO cliente){
        clienteRedisService.salvarNoCache(cliente);
    }

    @GetMapping("buscar-filtro")
    public ResponseEntity<ClienteDTO> buscarClientePorCPF(@RequestParam String cpf) {
        Optional<ClienteDTO> cliente = clienteService.buscaClientePorCPF(cpf);

        if (cliente.isPresent()) {
            clienteRedisService.salvarNoCache(cliente.get());
            return ResponseEntity.ok(cliente.get());
        }

        // Retorna 404 se o cliente não for encontrado
        return ResponseEntity.notFound().build();
    }


}
