# Projeto Biblioteca Java API
API de uma Biblioteca com cadastro de usuário, endereço, livros e locação de livros.
A aplicação está disponível para teste no link [API Biblioteca](https://academy-accenture-projeto-fina.herokuapp.com/swagger-ui.html#)
# Team Duke_Gama_Friends
	Ivan Domingos Moreira
	João Fagundes Villar Bernardes
	Jorge Henrique Dos Santos Ferraz
	José Victor
	Kainan Pinheiro
	Lazaro Marinho

# Estrutura do projeto
![](https://images2.imgbox.com/23/17/Mr4e3FzI_o.jpg)

# Tecnologias utilizadas
- [SpringBoot](https://spring.io/projects/spring-boot#)
- [Spring Data](https://spring.io/projects/spring-data-jpa#)
- [JSON Web Tokens](https://jwt.io/#)
- [PostgreSQL](https://www.postgresql.org/#)

#### Endpoints Diponíveis
##### Cadastro (LISTAR:GET):
https://academy-accenture-projeto-fina.herokuapp.com/cadastro

##### Cadastro (SALVAR:POST):
https://academy-accenture-projeto-fina.herokuapp.com/cadastro
```sh
{
  "cpf": "string",
  "email": "string",
  "endereco": {
    "bairro": "string",
    "cep": "string",
    "ibge": 0,
    "id": 0,
    "localidade": "string",
    "logradouro": "string",
    "numero": "string",
    "uf": "string"
  },
  "id": 0,
  "login": {
    "senha": "string",
    "usuario": "string"
  },
  "nome": "string",
  "telefone": "string"
}
```
##### Cadastro (BUSCARID:GET):

https://academy-accenture-projeto-fina.herokuapp.com/cadastro/{ID}

##### Livros (EXIBIRTODOS:GET):

https://academy-accenture-projeto-fina.herokuapp.com/livros

##### Livros (SALVAR:POST):
https://academy-accenture-projeto-fina.herokuapp.com/livros
```sh
{
  "exemplares": 0,
  "id": 0,
  "isbn": "string",
  "reservados": 0,
  "titulo": "string",
  "valorDiaria": 0
}
```
##### Livros (EXIBIRTODOS:GET):

https://academy-accenture-projeto-fina.herokuapp.com/locacao

##### Locação (EXIBIRTODOS:GET):

https://academy-accenture-projeto-fina.herokuapp.com/locacao

##### Locação (EXIBIRTODOS:GET):

https://academy-accenture-projeto-fina.herokuapp.com/locacao/{id}?id={ID}

##### Locação (AGENDAR:POST):
https://academy-accenture-projeto-fina.herokuapp.com/locacao/agendar
```sh
{
  "dataAgendamento": "yyyy-MM-dd",
  "dataFinalizacao": "yyyy-MM-dd",
  "dataRetirada": "yyyy-MM-dd",
  "locacaoItem": [
    {
      "dataPrevisaoEntrega": "yyyy-MM-dd",
      "livro_id": 0,
      "valorDiaria": 0
    }
  ],
  "status": "string",
  "usuario_id": 0,
  "usuario_name": "string"
}
```
##### Locação (BUSCAESPECIFICA:GET):

https://academy-accenture-projeto-fina.herokuapp.com/locacao/buscaEspeficica
| Campos que são passiveis de serem pesquisados          |
|:---------------------------|
| dataAgendamento      |
| dataFinalizacao      |
| dataRetirada      |
| locacaoItem[0].dataPrevisaoEntrega      |
| locacaoItem[0].livro_id      |
| locacaoItem[0].valorDiaria      |
| usuario_id      |
| usuario_name      |

##### Locação (ENTREGARLIVRO:PUT):

https://academy-accenture-projeto-fina.herokuapp.com/locacao/entregarLivro/{id}?id={ID}
##### Locação (RETIRARLIVRO:PUT):

https://academy-accenture-projeto-fina.herokuapp.com/locacao/retirar/{id}?id={ID}

##### Login (POST):
https://academy-accenture-projeto-fina.herokuapp.com/login:

```sh
{
  "senha": "string",
  "usuario": "string"
}
```
