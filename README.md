# 💰 Sistema Financeiro - Refatoração com SOLID e Strategy

## 📌 Sobre o Projeto
Este projeto demonstra a evolução de um sistema financeiro que inicialmente possuía um código monolítico, rígido e 
de difícil manutenção (**Antipattern - "O Caos"**), sendo posteriormente refatorado para uma arquitetura limpa, 
escalável e aderente aos princípios **SOLID**. O sistema permite: cadastro de ativos financeiros, 
cadastro de investidores, realização de investimentos, cálculo dinâmico de impostos e consulta de dados via API REST.

## 🎯 Objetivo
Demonstrar na prática: aplicação dos princípios **SOLID**, uso de **Arquitetura em Camadas**, implementação do padrão 
**Strategy**, separação de responsabilidades e evolução de código legado para código profissional.

## ⚠️ O CAOS (Antipattern)
A classe `SistemaFinanceiro` representa um sistema legado com diversos problemas.

### ❌ Problemas encontrados

#### 🔴 1. Violação do SRP (Single Responsibility Principle)
Uma única classe faz tudo: regra de negócio, persistência (JDBC), interface com usuário, cálculo de imposto e relatórios.

---

#### 🔴 2. Uso excessivo de if/else (Código rígido)
```java
if (inv.tipoInvestidor.equals("PF")) {
    if (inv.tipoInvestimento.equals("ACAO")) {
        imposto = inv.valor * 0.15;
    }
}
```
Problemas: difícil manutenção, alto risco de erro e não escalável.

---

#### 🔴 3. Violação do DIP (Dependency Inversion Principle)
```java
Connection conn = DriverManager.getConnection(...)
```
Problema: código depende diretamente do banco, impossibilitando troca de tecnologia.

---

#### 🔴 4. Violação do OCP (Open/Closed Principle)
Para adicionar um novo tipo, é necessário modificar vários if/else.

Problema: sistema não é extensível e está fortemente acoplado.

---

#### 🔴 5. Ausência de camadas

Tudo está dentro de uma única classe, sem separação de responsabilidades.

---

## ✅ A ORDEM (Solução)

### 🏗️ Arquitetura em Camadas

```bash
br.com.centroweg.financas
│
├── domain
│   ├── entities
│   ├── exceptions
|   └── interfaces
│
├── service
│   ├── usecases
│   ├── mapper
│   ├── dto
│
├── infra
│   ├── repository
│   └── strategies
│
└── web
    └── controllers
```

### 🧠 Aplicação dos Princípios SOLID

#### ✅ SRP (Responsabilidade Única)

Cada classe tem uma única responsabilidade: Ativo (entidade), AtivoMapper (conversão), ImpostoResolverService (cálculo de imposto), Controller (entrada HTTP).

---

#### ✅ OCP (Aberto/Fechado)

Novo tipo de imposto pode ser adicionado criando uma nova Strategy:

```java
@Component
public class ImpostoCriptoStrategy implements CalculoImpostoStrategy
```

---

#### ✅ LSP (Substituição de Liskov)

Qualquer Ativo pode ser utilizado sem quebrar o sistema:

```java
Ativo ativo = new Acao(...);
```

---

#### ✅ ISP (Segregação de Interface)

Interfaces específicas:

```java
public interface CalculoImpostoStrategy
```

---

#### ✅ DIP (Inversão de Dependência)

Service depende de abstrações:

```java
private final List<CalculoImpostoStrategy> strategies;
```

---

### 🔁 Padrão Strategy

#### 🎯 Problema resolvido

Eliminar múltiplos if/else no cálculo de imposto.

#### 💡 Implementação

Interface

```java
public interface CalculoImpostoStrategy {
    BigDecimal calcular(BigDecimal valor);
    boolean isAplicavel(Ativo ativo);
}
```

---

Estratégias

```java
ImpostoAcaoStrategy
ImpostoFundoImobiliarioStrategy
ImpostoRendaFixaStrategy
```

---

Resolver

```java
strategies.stream()
    .filter(strategy -> strategy.isAplicavel(ativo))
    .findFirst()
```

---

#### 🚀 Benefícios

Código limpo, extensível, fácil manutenção e sem condicionais complexas.

---

## 🔄 Fluxo do Sistema

### 📌 Cadastro de Ativo

- Controller recebe DTO
- Mapper converte para entidade
- Repository salva
- Strategy calcula imposto
- Retorna ResponseDTO

### 📌 Realização de Investimento

- Busca investidor
- Busca ativo
- Valida regras
- Calcula imposto
- Atualiza saldo
- Salva ordem

---

## 🌐 Endpoints da API

### 📊 Ativos

- GET /ativo
- GET /ativo/{ticker}
- GET /ativo/tipos
- POST /ativo

### 👤 Investidores

- GET /investidor
- GET /investidor/{id}
- POST /investidor
- PATCH /investidor/{id}/nome
- PATCH /investidor/{id}/saldo
- DELETE /investidor/{id}

### 💸 Investimentos

- POST /investimento
- GET /investimento/{id}
- GET /investimento/investidor/{id}

---

## 🧪 Exemplo de JSON

Criar Ação
```json
{
    "tipo": "RENDA_FIXA",
    "ticker": "CDB123",
    "nome": "CDB Banco XYZ",
    "valorAtual": 1000.50,
    "dataVencimento": "2027-12-31",
    "taxaContratada": 12.5,
    "indexador": "CDI"
}
```

---

## 🚀 Como Executar

- Clonar o projeto
- Configurar banco (MySQL/PostgreSQL)
- Rodar aplicação Spring Boot
```bash
mvn spring-boot:run
```

--- 

## 👩‍💻 Autores

<table align="center">
  <tr>
    <td align="center" width="160" height="200" style="border:2px solid #ccc;">
      <img src="https://github.com/Liiiiisssz.png" width="115" height="115"><br>
      <sub><a href="https://github.com/Liiiiisssz">Elis Jasper</a></sub>
    </td>
    <td align="center" width="160" height="200" style="border:2px solid #ccc;">
      <img src="https://github.com/KaelLuih.png" width="115" height="115"><br>
      <sub><a href="https://github.com/KaelLuih">Kael Luih de Araujo</a></sub>
    </td>
  </tr>
</table>
