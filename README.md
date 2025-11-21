# Caixa Desafio Investimento

Este projeto tem como objetivo fornecer uma API de investimentos utilizando o framework **Quarkus**e integração com **Keycloak** para autenticação e segurança.

## Como Subir a Aplicação

1. Clone o projeto
   ```bash
   git clone https://github.com/DaviLNogueira/caixa-desafio-investimento.git
   cd caixa-desafio-investimento
   ```



Para executar a aplicação, basta utilizar o comando abaixo:

```bash
./mvnw clean package -Dquarkus.container-image.build=true -DskipTests  && docker compose up --build -d
```
> **Observação:** Todas as integrações e configurações foram pensadas para execução **em modo de desenvolvimento**.
> Cerca de 4 minutos podem ser necessários para que todos os serviços estejam totalmente operacionais,
> já que temos o Keycloak, Banco de dados e a aplicação.

## Integrações

A aplicação faz integração direta com o Keycloak para autenticação dos usuários. O fluxo de login, obtenção de token e proteção das rotas está configurado para ambiente local.

## Rotas

Para consultar as rotas disponíveis, acesse

[http://localhost:8082/q/swagger-ui/](http://localhost:8082/q/swagger-ui/)

A documentação via Swagger permite visualizar todos os endpoints expostos, bem como realizar testes diretamente pela interface.Também pode consuntar no arquivo `openapi.yaml` na pasta docs.
A aplicação está a executar em `http://localhost:8082`

> Não é possível a execução dos testes via Swagger, pois a autenticação via Keycloak não foi estabelecida pela configuração do mapeamneto de rede entre os container.
## Testes via Insomnia



O projeto acompanha um arquivo do Insomnia com todos os parâmetros já configurados para facilitar o teste dos endpoints e autenticação via Keycloak.

**Passos para importar:**
1. Abra o Insomnia.
2. Importe o arquivo `insomnia.json` que está na raiz do projeto.
3. Todos os endpoints já estarão configurados, incluindo as chaves de acesso para autenticação.
4. Execute a requisição de "Obter Token" para autenticar, com isso as outras rotas teram intregração automática com o toquen desejada.
rias, abra uma issue neste repositório!
5. Tipo disponiveis para testes: 'CDB', 'TESOURO', 'FII', 'ACAO', 'LCI', 'FUNDO', 'POUPANCA', 'FIXA'

> Se você encontrar algum problema também pode executar através do script rotas.sh que está na pasta `docs`. Ele utiliza o curl para testar todas as rotas da aplicação automaticamente

## Observação 

Caso necessite testar as rotas de outra maneira, utilize as seguintes credenciais e configurações para o Keycloak:

- **URL do Keycloak:** `http://localhost:8080`
- **Usuário Keycloak (admin):** `admin`
- **Senha Keycloak (admin):** `admin_password`

Para autenticação via API, utilize:

- **username:** `system-manage`
- **password:** `1234`
## Documentação Adicional

Na pasta docs, você encontrará um arquivos  com informações detalhadas sobre decisões, modelo de banco de daos, apis adotadas durante o desenvolvimento da aplicação.
Além que no github a um rastreio de commits que detalham o desenvolvimento passo a passo com as issues.


>Não foi possível a realização de inserção de dados dos produtos associados.Por esse motivo é disponibilizado o script de inserção se necessário na raiz do projeto.insercao.sql.