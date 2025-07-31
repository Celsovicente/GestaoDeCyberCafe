/**********************************
File: Analise.java
Tema: Gestao de um Cyber Cafe
Nome: Tinilsa Domingos
Numero: 34298
Data: 19.06.2025
**********************************/

/*
1. Objectivo do projecto:
Desenvolver um sistema que permita controlar o acesso de clientes 
a computadores de um cyber cafe mediante o pagamento por tempo de uso.
*/

/*
2. Visao [Interfaces Graficas]
- ApresentacaoVisao       // Tela inicial do sistema
- LoginVisao              // Tela de login do funcionario
- MenuPrincipal           // Menu apos o login
- ClienteVisao            // Cadastro e consulta de clientes
- EstacaoVisao            // Visualizacao das estacoes de computador
- SessaoVisao             // Abertura e encerramento de sessoes
- ConfiguracaoVisao       // Definicoes do sistema
*/

/*
3. Entidades Fortes e Seus Atributos (Modelo)

- ClienteModelo
    int id
    String nome
    String telefone
    String genero                    // Ex: Masculino, Feminino (pode virar tabela)
    String provincia                 // Ligado a Provincia.tab
    String municipio                // Ligado a Municipio.tab
    String comuna                   // Ligado a Comuna.tab
    String estado                   // Ex: Activo/Inactivo
    String horarioInicio
    String horarioFim
    float custo

- EstacaoModelo
    int id
    int clientID
    boolean status                   // True = Ocupada, False = Livre

- SessaoModelo
    int id
    int clientID
    int estacaoID
    String horarioInicio
    String horarioFim
    double custo

- ConfiguracaoModelo
    int id
    float valorPorHora              // Preco cobrado por hora
    int totalEstacoes               // Total de estacoes disponiveis
*/

/*
4. Ficheiros
- ClienteFile.dat
- EstacaoFile.dat
- SessaoFile.dat
- ConfiguracaoFile.dat
*/

/*
5. Tabelas de Apoio (Entidades Fracas)
Estas tabelas ajudam a padronizar e evitar redundancia. Elas estao ligadas aos campos do ClienteModelo:

- Provincia.tab       -> Usada no campo `provincia` (lista de provincias)
- Municipio.tab       -> Usada no campo `municipio` (cada municipio pertence a uma provincia)
- Comuna.tab          -> Usada no campo `comuna` (cada comuna pertence a um municipio)
- Genero.tab          -> (opcional) Masculino, Feminino, Outro
- Estado.tab          -> (opcional) Activo, Inactivo
*/

/*
Hierarquia das Tabelas Auxiliares:
[Provincia] --> [Municipio] --> [Comuna]

Cada Cliente:
- escolhe uma Provincia (ex: Luanda)
    - dentro da Provincia escolhe um Municipio (ex: Cazenga)
        - dentro do Municipio escolhe uma Comuna (ex: Tala Hady)
*/

/*
6. Listagens e Pesquisas
- Listagem geral de Clientes
- Pesquisa de Cliente por Nome
- Relatorio de Sessoes por Data ou Cliente
*/

/*
7. Diversos
7.1 - Implementacao: Java com Swing
7.2 - IDE: Notepad++
*/
