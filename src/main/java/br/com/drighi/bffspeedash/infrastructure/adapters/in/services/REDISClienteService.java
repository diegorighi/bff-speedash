package br.com.drighi.bffspeedash.infrastructure.adapters.in.services;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.domain.model.cliente.DocumentoDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class REDISClienteService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CACHE_PREFIX = "cliente:cpf:";

    public REDISClienteService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void salvarNoCache(ClienteDTO cliente) {
        // Obtém o CPF do primeiro documento da lista
        String cpf = cliente.listaDocumentos().stream()
                .filter(doc -> "CPF".equals(doc.tipoDocumento().toString()))
                .map(DocumentoDTO::numeroDocumento)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("CPF não encontrado"));

        String cacheKey = CACHE_PREFIX + cpf;

        // Salvar no Redis com tempo de expiração de 10 minutos
        redisTemplate.opsForValue().set(cacheKey, cliente, Duration.ofMinutes(10));
    }
}

