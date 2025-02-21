package br.com.drighi.bffspeedash.application.usecases.cliente;

import br.com.drighi.bffspeedash.domain.model.cliente.ClienteDTO;
import br.com.drighi.bffspeedash.application.port.out.OutboundOpenFeignPort;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

public class ClienteCriarUseCase {

    private final static Integer MAIOR_DE_IDADE = 18;
    private final static String ERRO_MAIOR_IDADE = "Cliente deve ser maior de idade";
    private final static String ERRO_VALIDACAO = "Erro de validação";

    private final Validator validator;

    private final OutboundOpenFeignPort clientePort;

    public ClienteCriarUseCase(OutboundOpenFeignPort clientePort) {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.clientePort = clientePort;
    }

    /**
     * Método principal para criar um cliente no sistema.
     *
     * @param clienteDTO Objeto contendo os dados do cliente a ser criado.
     * @return ClienteDTO criado com sucesso.
     * @throws IllegalArgumentException Se os dados fornecidos forem inválidos.
     */
    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            throw new IllegalArgumentException("O argumento ClienteDTO não pode ser nulo");
        }

        // Valida o DTO antes de realizar qualquer operação
        validarCliente(clienteDTO);

        // Verifica regras de negócio (exemplo: somente clientes maiores de idade)
        if (!isMaiorDeIdade(clienteDTO.dadosPessoais().dataNascimento())) {
            throw new IllegalArgumentException(ERRO_MAIOR_IDADE);
        }

        // Retorna o cliente criado
        return enviaFormularoCliente(clienteDTO);
    }

    /**
     * Método para validar os dados do cliente usando Jakarta Bean Validation.
     */
    private void validarCliente(ClienteDTO clienteDTO) {
        Set<ConstraintViolation<ClienteDTO>> violacoes = validator.validate(clienteDTO);

        if (!violacoes.isEmpty()) {
            StringBuilder erros = new StringBuilder(ERRO_VALIDACAO);
            for (ConstraintViolation<ClienteDTO> violacao : violacoes) {
                erros.append(" ").append(violacao.getMessage()).append(";");
            }
            throw new IllegalArgumentException(erros.toString());
        }
    }

    /**
     * Método fictício para validar se o cliente é maior de idade.
     * TODO: Deverá ser substituido por um CHAIN OF RESPONSABILITY NO FUTURO
     */
    private boolean isMaiorDeIdade(String dataNascimento) {
        // Pattern "yyyy-MM-dd"
        LocalDate dataNasc = LocalDate.parse(dataNascimento);
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNasc, hoje).getYears() >= MAIOR_DE_IDADE;
    }

    /**
     * Envia para o CORE onde será persistido o cliente
     */
    private ClienteDTO enviaFormularoCliente(ClienteDTO clienteDTO) {
        return clientePort.salvarCliente(clienteDTO);
    }


}
