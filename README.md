# **BFF Speedash**
Bem-vindo ao repositório do **BFF Speedash**, um projeto desenvolvido com **Spring Boot** e **Spring Cloud** para oferecer uma camada de Backend For Frontend (BFF), facilitando a integração entre o front-end e os serviços de backend. Este projeto utiliza **Redis** para caching, **GraphQL** para operar consultas otimizadas idempotentes, validações baseadas em **Bean Validation** e comunicação não-idempotentes via HTTP RESTful com o **CORE Speedash** externas via **OpenFeign**.
## **Sobre o Projeto**
O BFF Speedash encapsula a lógica de negócio para o front-end, promovendo um design mais desacoplado e integrado a serviços externos. Ele segue boas práticas arquiteturais e utiliza ferramentas modernas para um desenvolvimento eficiente e escalável, além de seguir uma arquitetura **hexagonal** promovendo o desacoplamento da lógica de negócio independente do Framework.
### **Principais Funcionalidades**
- Gerenciamento avançado de **Clientes**, incluindo:
    - Validação de entrada com Jakarta Bean Validation.
    - Integração com serviços do **CORE** via **OpenFeign**.
- Gerenciamento avançado de **Veículos** para:
    - Adicionar veículo à sua conta.
    - Inativar veículo
    - Remover veículo
- Gerenciamento de **TAGS**
    - Emissão de novas tags
    - Ativação de tags
    - Cancelamento de tags
- Gerenciamento de **Meios de Pagamento**
    - Pagamento via PIX
    - Pagamento por Cartão de Crédito
    - Pagamento por Boleto Bancário
- Administração total da sua **Conta**
    - Consultar saldo
    - Adicionar saldo
    - Buscar veículos atrelados à conta
    - Buscar tags
    - Alterar endereço de cobrança e postal

- Suporte a **GraphQL**, permitindo consultas otimizadas e flexíveis.
- Uso de **Redis** para caching e otimização de performance.
- Monitoramento com **Actuator** e métricas via Micrometer integrado ao **Dynatrace**.
- Testes unitários e de integração usando **JUnit 5**, **Mockito** e **Spring GraphQL Test**.

## **Tecnologias Utilizadas**
O projeto utiliza as seguintes tecnologias principais:
- **Java 17**: Utilização de records para garantir a imutabilidade dos dados do CORE e cliente.
- **Spring Boot 3.4.2**: Framework principal para criação de aplicações Java modernas.
- **Spring Cloud**: Para integração com serviços distribuídos, incluindo o suporte ao **OpenFeign**.
- **Spring GraphQL**: Suporte ao GraphQL nativo do Spring.
- **Redis**: Armazenamento em cache para melhorar a performance.
- **Jakarta Bean Validation**: Validação de dados de entrada.
- **Micrometer e Dynatrace**: Coleta e monitoramento de métricas.
- **Spring WebFlux**: Suporte reativo para APIs assíncronas.

Ferramentas para **testes**:
- **JUnit 5**: Framework de testes.
- **Mockito**: Mocking de dependências e fluxos.
- **Spring Boot Starter Test**: Suporte para testes Spring integrados e unitários.
- **Spring GraphQL Test**: Para validar consultas e mutações em GraphQL.

## **Pré-requisitos**
Antes de começar, você precisará ter os seguintes itens instalados em sua máquina:
- **Java 17** ou superior.
- **Maven 4.x** para gerenciamento de dependências.
- Docker (opcional, mas recomendado) para execução do Redis localmente.

## **Como Executar o Projeto**
### **Passo 1: Clonar o Repositório**
``` bash
git clone https://github.com/seu_usuario/bff-speedash.git
cd bff-speedash
```

### **asso 2: Configurar o Ambiente**
Caso você precise de um ambiente Redis para caching, você pode usar o seguinte comando para rodá-lo via Docker:
``` bash
docker run --name redis -p 6379:6379 -d redis
```

### **Passo 3: Executar a Aplicação**
Execute o projeto com o Maven ou diretamente pela sua IDE preferida (recomendado: IntelliJ IDEA).
#### Usando Maven:
``` bash
mvn spring-boot:run
```

#### Ou, caso prefira, construa o JAR:
``` bash
mvn clean package
java -jar target/bff-speedash-0.0.1-SNAPSHOT.jar
```
A aplicação iniciará em **[http://localhost:8080](http://localhost:8080)**.
## **Endpoints Disponíveis**
### **GraphQL**
- URL para consultas GraphQL:
    - **[http://localhost:8080/graphql](http://localhost:8080/graphql)**

- Usando a interface GraphiQL:
    - **[http://localhost:8080/graphiql](http://localhost:8080/graphiql)**

Exemplo de consulta GraphQL:
query {
  cliente(id: "123") {
    nome
    documentos {
      numero
      tipo
    }
  }
}

### **Actuator**
Verifique a saúde e métricas da aplicação:
- **[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)**
- **[http://localhost:8080/actuator/metrics](http://localhost:8080/actuator/metrics)**

## **Testes**
Este projeto inclui uma cobertura extensa de testes:
### **Como Rodar os Testes**
Use o comando abaixo para executar todos os testes implementados:
``` bash
mvn test
```

Os testes incluem:
- **Testes Unitários**: Validação de fluxos principais de casos de uso.
- **Testes com Mockito**: Mocking para cenários específicos.
- **Testes GraphQL**: Validação de schemas e consultas GraphQL



