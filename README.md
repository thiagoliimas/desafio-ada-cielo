  # <h1 align="center">DESAFIO ADA TECH E CIELO</h1>

<p align="center">Esse projeto faz parte do desafio final do Bootcamp Ada & Cielo. O desafio consistia em criar uma api que armazene uma lista de cliente em potenciais para posteriormente poderem ser contactados
pelo time de vendas. As tecnologias usadas incluem Java 17, SpringBoot 3.0, Banco em mémoria H2, Amazon AWS - SQS (Sistema de mensageria AWS) e documentação com Swagger</p>

<h4 align="center"> 
	🚀 Concluído 🚀
</h4>

### Features

- [x] Cadastro de pessoa física
- [x] Busca de pessoa física
- [x] Edição de pessoa física
- [x] Exclusão de pessoa física
- [x] Prospect de pessoa física
###
- [x] Busca de pessoa jurídica
- [x] Edição de pessoa jurídica
- [x] Exclusão de pessoa jurídica
- [x] Prospect de pessoa jurídica

### Mais sobre o projeto

Dentro do repositório principal o projeto foi dividido em 4 pastas de acordo com cada tópico que foi pedido ao longo do desafio.

Na pasta Desafio1 está toda a estruruta do projeto necessárias aos endpoints como: controllers, services,repositories e models, além de algumas outras auxiliares da programação.

O dentro do desafio 1, usado nos endpoints, estão as aplicaçãoes do desafio 2, 3 e 4.

O projeto está configurado para persistir os objetos no banco em memória H2.

A parte de implementação do desafio 2 (criação de filas em mémoria) no desafio 1 está comentado pois posteriormente foi substituído pelo implementação do desafio 3 que faz uso do SQS da AWS que gerencia mensagens
em fila.

Por útimo temos a implementação do desafio 4 refente sobre segurança, autenticação e autorização.

As Exceptions estão sendo tratadas na pasta: src/main/java/br/com/desafiocielo/desafio1/infra/ExceptionalHandler.java

O desafio 4 (parte de segurança) está implemntado no desafio 1, suas classe serão encontradas nas pastas do desafio1
