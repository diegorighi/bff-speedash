package br.com.drighi.bffspeedash.usecases;

import br.com.drighi.bffspeedash.application.usecases.cliente.ClienteCriarUseCase;
import br.com.drighi.bffspeedash.domain.model.cliente.*;
import br.com.drighi.bffspeedash.domain.model.common.EnderecoDTO;
import br.com.drighi.bffspeedash.domain.model.common.EnumEstado;
import br.com.drighi.bffspeedash.domain.model.common.EnumOrgaoEmissor;
import br.com.drighi.bffspeedash.domain.model.common.EnumTipoDocumento;
import br.com.drighi.bffspeedash.application.port.out.OutboundOpenFeignPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteCriarUseCaseTest {

    @Mock
    private OutboundOpenFeignPort clientePort;

    @InjectMocks
    private ClienteCriarUseCase clienteCriarUseCase;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setup() {
        clienteDTO = new ClienteDTO(
                new PessoaDTO("João", null, "Silva", "1985-10-22", EnumSexo.M),
                List.of(new DocumentoDTO(EnumTipoDocumento.CPF, "12345678901",
                        EnumOrgaoEmissor.SSP, EnumEstado.SP, "2010-05-20", "2030-05-20")),
                List.of(new ContatoDTO(EnumTipoContato.CELULAR, "11987654321")),
                new EnderecoDTO("Rua Exemplo", 123, "Apto 12", "Bairro Exemplo", "12345678", "São Paulo", EnumEstado.SP)
        );
    }

    @Test
    void deveCriarClienteComSucesso() {
        // Cenário
        when(clientePort.salvarCliente(clienteDTO)).thenReturn(clienteDTO);

        // Execução
        ClienteDTO resultado = clienteCriarUseCase.criarCliente(clienteDTO);

        // Verificação
        assertNotNull(resultado);
        assertEquals(clienteDTO, resultado);
        verify(clientePort, times(1)).salvarCliente(clienteDTO);
    }

    @Test
    void deveLancarErroSeClienteDTOForNulo() {
        // Execução e verificação
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteCriarUseCase.criarCliente(null) // DTO nulo
        );

        // Validação de mensagem apropriada
        assertEquals("O argumento ClienteDTO não pode ser nulo", exception.getMessage());

        // Verifica que o método salvarCliente nunca foi chamado
        verify(clientePort, never()).salvarCliente(any());
    }


    @Test
    void deveLancarErroSeDadosPessoaisForemInvalidos() {
        // Execução e verificação
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(
                    null, // Dados pessoais nulos
                    clienteDTO.listaDocumentos(),
                    clienteDTO.listaContatos(),
                    clienteDTO.enderecoDTO()
            );
        });

        assertEquals("Dados Pessoais nunca devem estar vazios", exception.getMessage());
    }

    @Test
    void deveLancarErroSeListaDeDocumentosForVazia() {
        // Execução e verificação
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(
                    clienteDTO.dadosPessoais(),
                    Collections.emptyList(), // Lista de documentos vazia
                    clienteDTO.listaContatos(),
                    clienteDTO.enderecoDTO()
            );
        });

        assertEquals("Ao menos deve conter um documento", exception.getMessage());
    }

    @Test
    void deveLancarErroSeListaDeContatosForVazia() {
        // Execução e verificação
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(
                    clienteDTO.dadosPessoais(),
                    clienteDTO.listaDocumentos(),
                    Collections.emptyList(), // Lista de contatos vazia
                    clienteDTO.enderecoDTO()
            );
        });

        assertEquals("Ao menos deve conter um contato", exception.getMessage());
    }

    @Test
    void deveLancarErroSeEnderecoForNulo() {
        // Execução e verificação
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(
                    clienteDTO.dadosPessoais(),
                    clienteDTO.listaDocumentos(),
                    clienteDTO.listaContatos(),
                    null // Endereço nulo
            );
        });

        assertEquals("O endereço não pode estar vazio", exception.getMessage());
    }
}