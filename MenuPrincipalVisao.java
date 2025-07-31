/*------------------------------------
Tema: Gestão de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: MenuPrincipalVisao.java
Data: 30/07/2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import SwingComponents.*;
import Calendario.*;

public class MenuPrincipalVisao extends JFrame implements ActionListener
{
    private JMenuBar menuBar;
    private JMenu clienteMenu, estacaoMenu, sessaoMenu,configuracaoMenu, listagemMenu, ajudaMenu, tabelaMenu;
    private JMenuItem novoClienteItem, editarClienteItem, eliminarClienteItem, sairClienteItem;
    private JMenuItem novaSessaoItem, editarSessaoItem, eliminarSessaoItem;
    private JMenuItem novaConfiguracaoItem, editarConfiguracaoItem, eliminarConfigiracaoItem;
    private JMenuItem novaEstacaoItem, editarEstacaoItem, eliminarEstacaoItem;
    private JMenuItem listarClienteItem, pesquisarClienteItem, listarSessaoItem, pesquisarSessaoItem,
    listarEstacaoItem, pesquisarEstacaoItem, listarConfiguracaoItem, pesquisarConfiguracaoItem;
    private JMenuItem provinciaItem, municipioItem, comunaItem,horarioInicioItem, horarioFimItem; 
    
    public MenuPrincipalVisao(String user)
    {
        super("Menu Principal | Operador " + user);

        instanciarObjetos();

        setJMenuBar(menuBar);

        setSize(800, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void instanciarObjetos()
    {
        // instanciando o menuBar
        menuBar = new JMenuBar();

        // instanciando os elementos do menuBar
        menuBar.add(clienteMenu = new JMenu("Cliente"));
        clienteMenu.setMnemonic('C');
        clienteMenu.setIcon(new ImageIcon("image/funcionario24.png"));
        menuBar.add(estacaoMenu = new JMenu("Estacao"));
        estacaoMenu.setMnemonic('E');
        menuBar.add(sessaoMenu = new JMenu("Sessao"));
        sessaoMenu.setMnemonic('S');
        menuBar.add(configuracaoMenu = new JMenu("Configuracao"));
        configuracaoMenu.setMnemonic('C');
        menuBar.add(listagemMenu = new JMenu("Listagens/Pesquisas"));
        listagemMenu.setIcon(new ImageIcon("image/search32.png"));
        listagemMenu.setMnemonic('L');
        menuBar.add(tabelaMenu = new JMenu("Tabela"));
        tabelaMenu.setMnemonic('T');
        tabelaMenu.setIcon(new ImageIcon("image/tabela.png"));

        // instanciando os elementos do menuCliente
        clienteMenu.add(novoClienteItem = new JMenuItem("Novo Cliente", new ImageIcon("image/novo24.png")));
        novoClienteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        clienteMenu.add(editarClienteItem = new JMenuItem("Editar", new ImageIcon("image/edit24.png")));
        clienteMenu.add(eliminarClienteItem = new JMenuItem("Eliminar", new ImageIcon("image/delete24.png")));
        clienteMenu.addSeparator();
        clienteMenu.add(sairClienteItem = new JMenuItem("Sair", new ImageIcon("image/logout24.png")));

        // instanciando os elementos do menuEstacao
        estacaoMenu.add(novaEstacaoItem = new JMenuItem("Nova Estacao", new ImageIcon("image/novo24.png")));
        novaEstacaoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        estacaoMenu.add(editarEstacaoItem = new JMenuItem("Editar", new ImageIcon("image/edit24.png")));
        estacaoMenu.add(eliminarEstacaoItem = new JMenuItem("Eliminar", new ImageIcon("image/delete24.png")));

        // instanciando os elementos do menuSessao
        sessaoMenu.add(novaSessaoItem = new JMenuItem("Nova Sessao", new ImageIcon("image/novo24.png")));
        novaSessaoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        sessaoMenu.add(editarSessaoItem = new JMenuItem("Editar", new ImageIcon("image/edit24.png")));
        sessaoMenu.add(eliminarSessaoItem = new JMenuItem("Eliminar", new ImageIcon("image/delete24.png")));

        // instanciando os elementos do menuConfiguracao
        configuracaoMenu.add(novaConfiguracaoItem = new JMenuItem("Nova Configuracao", new ImageIcon("image/novo24.png")));
        novaConfiguracaoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        configuracaoMenu.add(editarConfiguracaoItem = new JMenuItem("Editar", new ImageIcon("image/edit24.png")));
        configuracaoMenu.add(eliminarConfigiracaoItem = new JMenuItem("Eliminar", new ImageIcon("image/delete24.png")));

        // instanciando os elementos do menuListagem
        listagemMenu.add(listarClienteItem = new JMenuItem("Listar Clientes"));
        listagemMenu.add(pesquisarClienteItem = new JMenuItem("Pesquisar Clientes"));
        listagemMenu.addSeparator();
        listagemMenu.add(listarSessaoItem = new JMenuItem("Listar Sessao"));
        listagemMenu.add(pesquisarSessaoItem = new JMenuItem("Pesquisar Sessao"));
        listagemMenu.addSeparator();
        listagemMenu.add(listarEstacaoItem = new JMenuItem("Listar Estacao"));
        listagemMenu.add(pesquisarEstacaoItem = new JMenuItem("Pesquisar Estacao"));
        listagemMenu.addSeparator();
        listagemMenu.add(listarConfiguracaoItem = new JMenuItem("Listar Configuracao"));
        listagemMenu.add(pesquisarConfiguracaoItem = new JMenuItem("Pesquisar Configuracao"));

        // instanciar os elementos do menuTabela
        tabelaMenu.add(provinciaItem = new JMenuItem("Provincia"));
        tabelaMenu.add(municipioItem = new JMenuItem("Municipio"));
        tabelaMenu.add(comunaItem = new JMenuItem("Comuna"));
        tabelaMenu.add(horarioInicioItem = new JMenuItem("Hora de Inicio"));
        tabelaMenu.add(horarioFimItem = new JMenuItem("Hora de Termino"));

        // adicionando evento nos elementos do menuCliente
        novoClienteItem.addActionListener(this);
        editarClienteItem.addActionListener(this);
        eliminarClienteItem.addActionListener(this);
        sairClienteItem.addActionListener(this);
        listarClienteItem.addActionListener(this);
        pesquisarClienteItem.addActionListener(this);

        // adicionando evento nos elementos do menuSessao
        novaSessaoItem.addActionListener(this);
        editarSessaoItem.addActionListener(this);
        eliminarSessaoItem.addActionListener(this);
        pesquisarSessaoItem.addActionListener(this);
        listarSessaoItem.addActionListener(this);

        // adicionando evento nos elementos do menuEstacao
        novaEstacaoItem.addActionListener(this);
        editarEstacaoItem.addActionListener(this);
        eliminarEstacaoItem.addActionListener(this);
        listarEstacaoItem.addActionListener(this);
        pesquisarEstacaoItem.addActionListener(this);

        // adicionando evento nos elementos do menuConfiguracao
        novaConfiguracaoItem.addActionListener(this);
        editarConfiguracaoItem.addActionListener(this);
        eliminarConfigiracaoItem.addActionListener(this);
        listarConfiguracaoItem.addActionListener(this);
        pesquisarConfiguracaoItem.addActionListener(this);

        // adicionando evento nos elementos do menuTabela
        provinciaItem.addActionListener(this);
        municipioItem.addActionListener(this);
        comunaItem.addActionListener(this);
        horarioInicioItem.addActionListener(this);
        horarioFimItem.addActionListener(this);
    }

	public void actionPerformed(ActionEvent event)	
    {
        if(event.getSource() == novoClienteItem)
            new ClienteVisao(false, new ClienteModelo());
        else if(event.getSource() == editarClienteItem)
            new EditarCliente();
        else if(event.getSource() == eliminarClienteItem)
            new EliminarCliente();
        else if(event.getSource() == pesquisarClienteItem)
            new PesquisarCliente();
        else if(event.getSource() == listarClienteItem)
            ClienteFile.listarClientes();
        else if(event.getSource() == novaConfiguracaoItem)
            new ConfiguracaoVisao(false, new ConfiguracaoModelo());
        else if(event.getSource() == editarConfiguracaoItem)
            new EditarConfiguracao();
        else if(event.getSource() == eliminarConfigiracaoItem)
            new EliminarConfiguracao();
        else if(event.getSource() == pesquisarConfiguracaoItem)
            new PesquisarConfiguracao();
        else if(event.getSource() == listarConfiguracaoItem)
            ConfiguracaoFile.listarConfiguracoes();
        else if(event.getSource() == novaEstacaoItem)
            new EstacaoVisao(false, new EstacaoModelo());
        else if(event.getSource() == editarEstacaoItem)
            new EditarEstacao();
        else if(event.getSource() == eliminarEstacaoItem)
            new EliminarEstacao();
        else if(event.getSource() == pesquisarEstacaoItem)
            new PesquisarEstacao();
        else if(event.getSource() == listarEstacaoItem)
            EstacaoFile.listarEstacao(); 
        else if(event.getSource() == novaSessaoItem)
            new SessaoVisao(false, new SessaoModelo());
        else if(event.getSource() == editarSessaoItem)
            new EditarSessao();
        else if(event.getSource() == eliminarSessaoItem)
            new EliminarSessao();
        else if(event.getSource() == pesquisarSessaoItem)
            new PesquisarSessao();
        else if(event.getSource() == listarSessaoItem)
            SessaoFile.listarSessoes();
        else if(event.getSource() == provinciaItem)
            Tabela2.editarNovosItems("Provincias.tab", "Nova Provincia");
        else if(event.getSource() == municipioItem)
            Tabela3_2.editarNovosItems("Provincias.tab", "Municipios.tab", "Provincia", "Municipio", "Novo Municipio");
        else if(event.getSource() == comunaItem)
            Tabela3_3.editarNovosItems("Provincias.tab", "Municipios.tab", "Comunas.tab", 
            "Provincia", "Municipio", "Comuna", "Nova Comuna");
        else if(event.getSource() == horarioInicioItem)
            Tabela2.editarNovosItems("HoraInicio.tab", "Nova Hora de Inicio");
        else if(event.getSource() == horarioFimItem)
            Tabela3_2.editarNovosItems("HoraInicio.tab", "HoraFim.tab", "Hora Inicio", 
            "Hora Termino", "Nova Hora de Termino");
        else if(event.getSource() == sairClienteItem)
            dispose();
    }

    public static void main(String[] args)
    {
		Vector_Tabelas.inic();
        new MenuPrincipalVisao("");
    }
}
