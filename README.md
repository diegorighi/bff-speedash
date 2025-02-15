# **BFF Speedash**
Bem-vindo ao repositório do **BFF Speedash**. Este é um projeto Back-end que implementa os casos de uso relacionados a clientes, utilizando o framework **Jakarta EE** integrado a boas práticas de padrões DDD (Domain-Driven Design). Ele também conta com testes unitários utilizando **JUnit 5** e **Mockito**.
## **Sobre o Projeto**
O BFF Speedash é um **Backend For Frontend (BFF)** que facilita a comunicação entre o cliente (front-end) e os serviços do domínio. Ele abstrai casos de uso de cadastro, validação e gerenciamento de clientes, fornecendo acesso a uma interface simplificada para o front-end do sistema.
### **Principais Funcionalidades**
- Criar um cliente validado para os critérios do sistema (exemplo: maioridade, validações de dados).
- Gerenciamento de **Documentos**, **Contatos** e **Endereços** de clientes.
- Arquitetura baseada em **DDD** (Domínio, Use Cases e Ports/Adapters).
- Aplicação de testes unitários com cobertura dos principais fluxos.

## **Tecnologias Utilizadas**
- **Java** (Jakarta EE)
- **JUnit 5** (Testes unitários)
- **Mockito** (Mocking de dependências e fluxos)
- **Maven** (Gerenciamento de dependências)
- **Hibernate Validator** (Jakarta Bean Validation)
- **Lombok** (Redução de boilerplate em código)

## **Pré-requisitos**
Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:
- **Java 17** ou superior.
- **Maven 4.0+**.
- Um IDE de sua escolha (_IntelliJ IDEA recomendada_).
- Para rodar os testes: **JUnit 5** e **Mockito-core** configurados no projeto.
