/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: Analise.java
Data: 19.06.2025
***********************************/ 

/*
1.Objectivo do projecto:
Oferecer um ambiente onde os clientes  possam acessar computaores e 
a internet mediante o pagamento de uma taxa.*/ 

/*
2. Visao [Interfaces Graficas]
- ApresentacaoVisao
- LoginVisao
- MenuPrincipal
- ClienteVisao
- EstacaoVisao
- SessaoVisao
- ConfiguracaoVisao

3. Entidades Fortes e Seus Atributos (Modelo)
- ClienteModelo
    int id
	String nome
    String telefone
	String genero
	String provincia
	String municipio
	String comuna
	String horarioInicio
	String horarioFim
	float custo
	String estado  //  Activo/Inactivo
	boolean status
  
- EstacaoModelo
    int id
	ClienteModelo clientID
    String estado  // True = Ocupada, False = Livre
	boolean status  

- SessaoModelo 
    int id
    ClienteModelo clientID
    EstacaoModelo estacaoID 
    String horarioInicio
    String horarioFim
	double custo
	String dataSessao 
	boolean status

- Configuracao
	int id
    float valorPorHora              // Preco cobrado por hora
    int totalEstacoes               // Total de estacoes disponiveis
	boolean status
	
4. Ficheiro
- ClienteFile.dat
- EstacaoFile.dat
- SessaoFile.dat
- Configuracao.dat

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
- Provincias.tab
- Municipios.tab
- Comunas.tab
- HoraInicio.tab
- HoraFim.tab

6. Listagens e Pesquisas
- Listagem geral de Cliente
	Pesquisar Cliente Por Nome
	Pesquisar Cliente Por Telefone
- Listagem Geral da Configuracao
	Pesquisar Configuracao por Id
	Pesquisar Configuracao por Valor
- Listagem Geral de Estacao
	Pesquisa da Estacao Por Id
	Pesquisa da Estacao Por Estado
- Pesquisa Geral da Sessao
	Pesquisa Por Id
	Pesquisa Por HoraDeInicio

7. Diversos
7.1 - Implementação: Java Swing
7.2 - IDE: Notepad++
*/
