package br.com.drighi.bffspeedash.domain.model.conta;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.domain.model.cliente.CredencialDTO;
import br.com.drighi.bffspeedash.domain.model.pagamento.PagamentoDTO;
import br.com.drighi.bffspeedash.domain.model.tag.TagDTO;
import br.com.drighi.bffspeedash.domain.model.veiculo.VeiculoDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record ContaDTO(

        @NotNull(message = "O ID da conta não pode ser nulo ou inexistente")
        UUID contaID,

        @NotNull(message = "Uma conta sem cliente não é possivel existir")
        ClienteDTO cliente,

        @NotNull(message = "Uma conta deve conter uma credencial para existir")
        CredencialDTO credencial,

        List<VeiculoDTO> listaVeiculos,

        List<TagDTO> listaTags,

        List<PagamentoDTO> listaMeiosPagamento,

        @NotNull(message = "Toda conta criada deve ser FALSE antes do primeiro acesso")
        Boolean primeiroAcesso,

        @NotNull(message = "Após o primeiro acesso, o perfil deve constar como ativo")
        Boolean perfilAtivo,

        @NotNull(message = "A data de criação nunca pode ser nula")
        String dataCriacao


        ) {}
