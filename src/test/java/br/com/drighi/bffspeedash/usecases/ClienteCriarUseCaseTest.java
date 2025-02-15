package br.com.drighi.bffspeedash.usecases;

import br.com.drighi.bffspeedash.application.usercases.cliente.ClienteCriarUseCase;
import br.com.drighi.bffspeedash.domain.model.cliente.*;
import br.com.drighi.bffspeedash.domain.model.common.EnderecoDTO;
import br.com.drighi.bffspeedash.domain.model.common.EnumEstado;
import br.com.drighi.bffspeedash.domain.model.common.EnumOrgaoEmissor;
import br.com.drighi.bffspeedash.domain.model.common.EnumTipoDocumento;
import br.com.drighi.bffspeedash.domain.port.output.ClientePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ClienteCriarUseCaseTest {

    private ClientePort clientePort;
    private ClienteCriarUseCase clienteCriarUseCase;

    @BeforeEach
    void setup() {
        // Mock da porta (ClientePort)
        clientePort = Mockito.mock(ClientePort.class);

        // Instância do caso de uso
        clienteCriarUseCase = new ClienteCriarUseCase(clientePort);
    }

    @Test
    void deveCriarClienteComSucesso() {
        // Criar os dados de entrada válidos
        PessoaDTO pessoa = new PessoaDTO("John", null, "Doe", "2000-01-01", EnumSexo.M);
        DocumentoDTO documento = new DocumentoDTO(EnumTipoDocumento.RG, "12345678", EnumOrgaoEmissor.SSP, EnumEstado.SP, "2015-01-01", "2030-01-01");
        ContatoDTO contato = new ContatoDTO(EnumTipoContato.CELULAR, "11999999999");
        EnderecoDTO endereco = new EnderecoDTO("Rua Exemplo", 123, null, "Bairro Exemplo", "12345-678", "Cidade Exemplo", EnumEstado.SP);

        ClienteDTO inputCliente = new ClienteDTO(
                pessoa,
                List.of(documento),
                List.of(contato),
                endereco
        );

        // Mock do comportamento do clientePort
        when(clientePort.salvarCliente(inputCliente)).thenReturn(inputCliente);

        // Executa o caso de uso
        ClienteDTO clienteCriado = clienteCriarUseCase.criarCliente(inputCliente);

        // Verificações
        assertNotNull(clienteCriado);
        assertEquals("John", clienteCriado.dadosPessoais().primeiroNome());
        assertEquals("Doe", clienteCriado.dadosPessoais().sobrenome());
        verify(clientePort, times(1)).salvarCliente(inputCliente);
    }

    @Test
    void deveLancarErroParaClienteMenorDeIdade() {
        // Dados de entrada com data de nascimento de menor
        PessoaDTO pessoa = new PessoaDTO("Jane", null, "Doe", "2010-01-01", EnumSexo.F);
        DocumentoDTO documento = new DocumentoDTO(EnumTipoDocumento.RG, "98765432", EnumOrgaoEmissor.SSP, EnumEstado.RJ, "2020-05-01", "2030-05-01");
        ContatoDTO contato = new ContatoDTO(EnumTipoContato.EMAIL, "jane.doe@email.com");
        EnderecoDTO endereco = new EnderecoDTO("Rua Exemplo", 10, null, "Bairro Exemplo", "22345-678", "Cidade Exemplo", EnumEstado.RJ);

        ClienteDTO inputCliente = new ClienteDTO(
                pessoa,
                List.of(documento),
                List.of(contato),
                endereco
        );

        // Verifica lançamento de exceção para cliente menor de idade
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                clienteCriarUseCase.criarCliente(inputCliente)
        );

        // Verifica se a mensagem está correta
        assertEquals("Cliente deve ser maior de idade", exception.getMessage());

        // Garante que nenhum cliente foi enviado à persistência
        verify(clientePort, never()).salvarCliente(any());
    }

    @Test
    void deveLancarErroDeValidacaoParaDadosInvalidos() {
        // Criação do objeto com a lista de documentos vazia
        PessoaDTO pessoa = new PessoaDTO("John", null, "Doe", "2000-01-01", EnumSexo.M);
        ContatoDTO contato = new ContatoDTO(EnumTipoContato.CELULAR, "11999999999");
        EnderecoDTO endereco = new EnderecoDTO("Rua dos Inválidos", 123, null, "Centro", "12345-678", "Cidade Exemplo", EnumEstado.SP);

        // Validação ocorre no momento da criação do objeto ClienteDTO
        assertThrows(IllegalArgumentException.class, () -> {
            new ClienteDTO(
                    pessoa,
                    List.of(), // Lista de documentos vazia
                    List.of(contato), // Lista de contatos válida
                    endereco // Endereço válido
            );
        });
    }


    @Test
    void deveChamarPortaComDadosCorretos() {
        PessoaDTO pessoa = new PessoaDTO("John", null, "Doe", "1990-05-20", EnumSexo.M);
        DocumentoDTO documento = new DocumentoDTO(EnumTipoDocumento.CPF, "12345678901", EnumOrgaoEmissor.SSP, EnumEstado.SP, "2022-01-01", "2032-01-01");
        ContatoDTO contato = new ContatoDTO(EnumTipoContato.EMAIL, "john.doe@email.com");
        EnderecoDTO endereco = new EnderecoDTO("Alameda Santos", 1000, null, "Bela Vista", "01310-100", "São Paulo", EnumEstado.SP);

        ClienteDTO inputCliente = new ClienteDTO(pessoa, List.of(documento), List.of(contato), endereco);

        // Mock da porta
        when(clientePort.salvarCliente(any())).thenReturn(inputCliente);

        // Executa o caso de uso
        clienteCriarUseCase.criarCliente(inputCliente);

        // Captura argumentos passados para salvarCliente
        ArgumentCaptor<ClienteDTO> captor = ArgumentCaptor.forClass(ClienteDTO.class);
        verify(clientePort, times(1)).salvarCliente(captor.capture());

        // Valida os dados enviados
        ClienteDTO clienteEnviado = captor.getValue();
        assertEquals("John", clienteEnviado.dadosPessoais().primeiroNome());
        assertEquals("Doe", clienteEnviado.dadosPessoais().sobrenome());
        assertEquals("1990-05-20", clienteEnviado.dadosPessoais().dataNascimento());
    }



    @Test
    public void deveLancarErroSeEnderecoForNulo() {
        // Arrange
        PessoaDTO pessoa = new PessoaDTO(
                "João",
                "Silva",
                "Santos",
                "1985-10-25",
                EnumSexo.M
        );

        List<DocumentoDTO> documentos = List.of(new DocumentoDTO(
                EnumTipoDocumento.CPF,
                "123.456.789-00",
                EnumOrgaoEmissor.SSP,
                EnumEstado.SP,
                "2020-01-01",
                "2030-01-01"
        ));

        List<ContatoDTO> contatos = List.of(new ContatoDTO(EnumTipoContato.CELULAR, "+55 11 99999-9999"));

        // Act & Assert
        assertThatThrownBy(() -> new ClienteDTO(pessoa, documentos, contatos, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("O endereço não pode estar vazio");
    }

    @Test
    public void deveLancarErroSeDadosPessoaisForemNulos() {
        // Arrange
        List<DocumentoDTO> documentos = List.of(new DocumentoDTO(
                EnumTipoDocumento.CPF,
                "123.456.789-00",
                EnumOrgaoEmissor.SSP,
                EnumEstado.SP,
                "2020-01-01",
                "2030-01-01"
        ));

        List<ContatoDTO> contatos = List.of(new ContatoDTO(EnumTipoContato.CELULAR, "+55 11 99999-9999"));

        EnderecoDTO endereco = new EnderecoDTO(
                "Rua das Flores",
                123,
                "Apto 101",
                "Centro",
                "01000-000",
                "São Paulo",
                EnumEstado.SP
        );

        // Act & Assert
        assertThatThrownBy(() -> new ClienteDTO(null, documentos, contatos, endereco))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Dados Pessoais nunca devem estar vazios");
    }

    @Test
    public void deveLancarErroSeListaDeDocumentosForNula() {
        // Arrange
        PessoaDTO pessoa = new PessoaDTO(
                "João",
                "Silva",
                "Santos",
                "1985-10-25",
                EnumSexo.M
        );

        List<ContatoDTO> contatos = List.of(new ContatoDTO(EnumTipoContato.CELULAR, "+55 11 99999-9999"));

        EnderecoDTO endereco = new EnderecoDTO(
                "Rua das Flores",
                123,
                "Apto 101",
                "Centro",
                "01000-000",
                "São Paulo",
                EnumEstado.SP
        );

        // Act & Assert
        assertThatThrownBy(() -> new ClienteDTO(pessoa, null, contatos, endereco))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Ao menos deve conter um documento");
    }

    @Test
    public void deveLancarErroSeListaDeContatosForNula() {
        // Arrange
        PessoaDTO pessoa = new PessoaDTO(
                "João",
                "Silva",
                "Santos",
                "1985-10-25",
                EnumSexo.M
        );

        List<DocumentoDTO> documentos = List.of(new DocumentoDTO(
                EnumTipoDocumento.CPF,
                "123.456.789-00",
                EnumOrgaoEmissor.SSP,
                EnumEstado.SP,
                "2020-01-01",
                "2030-01-01"
        ));

        EnderecoDTO endereco = new EnderecoDTO(
                "Rua das Flores",
                123,
                "Apto 101",
                "Centro",
                "01000-000",
                "São Paulo",
                EnumEstado.SP
        );

        // Act & Assert
        assertThatThrownBy(() -> new ClienteDTO(pessoa, documentos, null, endereco))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Ao menos deve conter um contato");
    }


}
