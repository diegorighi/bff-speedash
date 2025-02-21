package br.com.drighi.bffspeedash.infrastructure.adapters.in.services;

import br.com.drighi.bffspeedash.application.port.in.InboundClienteHttpRest;
import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.infrastructure.adapters.out.http.ClienteFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements InboundClienteHttpRest {

    private final ClienteFeignClient clienteFeignClient;
    private final REDISClienteService redisClienteService;

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CACHE_PREFIX = "cliente:cpf:";

    public ClienteService(
            ClienteFeignClient clienteFeignClient,
            RedisTemplate<String, Object> redisTemplate,
            REDISClienteService redisClienteService
    ) {
        this.clienteFeignClient = clienteFeignClient;
        this.redisTemplate = redisTemplate;
        this.redisClienteService = redisClienteService;
    }




    public void salvarNoREDIS(ClienteDTO cliente){
        redisClienteService.salvarNoCache(cliente);
    }

    @Override
    public List<ClienteDTO> listaClientes() {
        return List.of();
    }

    @Override
    public Optional<ClienteDTO> buscaClientePorCPF(String cpf) {
        String cacheKey = CACHE_PREFIX + cpf;

        // Recupera o objeto do Redis (JSON) e força a deserialização para ClienteDTO
        Object cachedObject = redisTemplate.opsForValue().get(cacheKey);

        if (cachedObject != null) {
            ClienteDTO cliente = new ObjectMapper().convertValue(cachedObject, ClienteDTO.class);
            // Salvar novamente no cache apenas como precaução
            this.salvarNoREDIS(cliente);
            return Optional.of(cliente);
        }


        // 2️⃣ Se não estiver no cache, busca via Feign Client no CORE
        //Optional<ClienteDTO> cliente = Optional.ofNullable(clienteFeignClient.buscarClientePorCPF(cpf));

        // 3️⃣ Se encontrou, armazena no cache com expiração de 10 minutos
        //cliente.ifPresent(this::salvarNoREDIS);

        return null;
    }



}

