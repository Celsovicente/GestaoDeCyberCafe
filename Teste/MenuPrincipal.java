/**********************************
File: Analise.c
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos, 34298
Data: 19.06.2025
***********************************/
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class MenuPrincipal extends JFrame implements ActionListener
{

    private JMenu ficheiroMenu, operacoesMenu, listagensMenu, tabelasMenu, ajudaMenu;
	private JMenuItem novoClienteItem, alterarClienteItem, eliminarClienteItem, sairItem;
	private JMenuItem novaEstacaoItem, alterarEstacaoItem, eliminarEstacaoItem;
	private JMenuItem novaSessaoItem, alterarSessaoItem, eliminarSessaoItem;
    private JMenuItem novaConfiguracaoItem, alterarConfiguracaoItem, eliminarConfiguracaoItem;
    
	private JMenuItem listarClientesItem, listarSessoesItem, listarEstacoesItem, listarConfiguracoesItem; 
	private JMenuItem ConfiguracoesItem;
	private JMenuItem ajudaSobreAutorItem, ajudaSobreAplicacaoItem;
	private JMenuBar menuBar;
	private JPanel painelConteudo;

    public MenuPrincipal(){
        super("Menu Principal");
        
		menuBar = new JMenuBar();

        adicionarComponentes();

        setJMenuBar( menuBar );

		painelConteudo = new JPanel();
		painelConteudo.setLayout(new BorderLayout());
		add(painelConteudo, BorderLayout.CENTER);

        setSize(800, 700);
		setLocationRelativeTo(null);	//alinhar ao centro
		setVisible(true);
    }

        public void adicionarComponentes()
        {
             menuBar.add( ficheiroMenu = new JMenu("Ficheiro") );
		ficheiroMenu.setMnemonic('F');
		menuBar.add( operacoesMenu = new JMenu("Operacoes") );
		operacoesMenu.setMnemonic('O');
		menuBar.add( listagensMenu = new JMenu("Listagens") );
		listagensMenu.setMnemonic('L');
		menuBar.add( tabelasMenu = new JMenu("Tabelas") );
		tabelasMenu.setMnemonic('T');
		menuBar.add( ajudaMenu = new JMenu("Ajuda") );
		ajudaMenu.setMnemonic('A');

        ficheiroMenu.add( novoClienteItem = new JMenuItem("Novo Cliente"));
		ficheiroMenu.add( alterarClienteItem = new JMenuItem("Alterar Cliente"));
		ficheiroMenu.add( eliminarClienteItem = new JMenuItem("Eliminar Cliente"));
		ficheiroMenu.addSeparator();
		ficheiroMenu.add( sairItem = new JMenuItem("Sair"));

        
		novaSessaoItem = new JMenuItem("Nova Sessão");
		novaSessaoItem.addActionListener(e -> {
				painelConteudo.removeAll();
				painelConteudo.add(new NovaSessao(), BorderLayout.CENTER);
				painelConteudo.revalidate();
				painelConteudo.repaint();
		});
		operacoesMenu.add(novaSessaoItem);

		alterarSessaoItem = new JMenuItem("Alterar Sessão");
		alterarSessaoItem.addActionListener(e -> {
				painelConteudo.removeAll();
				painelConteudo.add(new AlterarSessao(), BorderLayout.CENTER);
				painelConteudo.revalidate();
				painelConteudo.repaint();
		});
		operacoesMenu.add(alterarSessaoItem);

		eliminarSessaoItem = new JMenuItem("Eliminar Sessão");
		eliminarSessaoItem.addActionListener(e -> {
				painelConteudo.removeAll();
				painelConteudo.add(new EliminarSessao(), BorderLayout.CENTER);
				painelConteudo.revalidate();
				painelConteudo.repaint();
		});
		operacoesMenu.add(eliminarSessaoItem);
		
        operacoesMenu.addSeparator();

        novaEstacaoItem = new JMenuItem("Nova Estacao");
		novaEstacaoItem.addActionListener(e -> {
				painelConteudo.removeAll();
				painelConteudo.add(new NovaEstacao(), BorderLayout.CENTER);
				painelConteudo.revalidate();
				painelConteudo.repaint();
		});
		operacoesMenu.add(novaEstacaoItem);

		alterarEstacaoItem = new JMenuItem("Alterar Estacao");
		alterarEstacaoItem.addActionListener(e -> {
				painelConteudo.removeAll();
				painelConteudo.add(new AlterarEstacao(), BorderLayout.CENTER);
				painelConteudo.revalidate();
				painelConteudo.repaint();
		});
		operacoesMenu.add(alterarEstacaoItem);

		eliminarEstacaoItem = new JMenuItem("Eliminar Estacao");
		eliminarEstacaoItem.addActionListener(e -> {
				painelConteudo.removeAll();
				painelConteudo.add(new EliminarEstacao(), BorderLayout.CENTER);
				painelConteudo.revalidate();
				painelConteudo.repaint();
		});
		operacoesMenu.add(eliminarEstacaoItem);

        operacoesMenu.addSeparator();

        operacoesMenu.add( novaConfiguracaoItem = new JMenuItem("Nova Configuracao") );
		operacoesMenu.add( alterarConfiguracaoItem  = new JMenuItem("Alterar configuracao") );
		operacoesMenu.add( eliminarConfiguracaoItem  = new JMenuItem("Eliminar configuracao") );

        listagensMenu.add( listarClientesItem = new JMenuItem("Listar Clientes"));
		listagensMenu.add( listarSessoesItem = new JMenuItem("Listar Sessões"));
		listagensMenu.add( listarEstacoesItem = new JMenuItem("Listar Estacões"));

		// Ações dos itens de menu
		listarClientesItem.addActionListener(e -> {
			painelConteudo.removeAll();
			painelConteudo.add(new ListarClientes(), BorderLayout.CENTER);
			painelConteudo.revalidate();
			painelConteudo.repaint();
		});
		listarSessoesItem.addActionListener(e -> {
			painelConteudo.removeAll();
			painelConteudo.add(new ListarSessao(), BorderLayout.CENTER);
			painelConteudo.revalidate();
			painelConteudo.repaint();
		});
		listarEstacoesItem.addActionListener(e -> {
			painelConteudo.removeAll();
			painelConteudo.add(new ListarEstacao(), BorderLayout.CENTER);
			painelConteudo.revalidate();
			painelConteudo.repaint();
		});


        tabelasMenu.add( ConfiguracoesItem = new JMenuItem("Configuracoes") );

       
        ajudaMenu.add( ajudaSobreAutorItem = new JMenuItem("Sobre o Autor") );
		ajudaMenu.add( ajudaSobreAplicacaoItem  = new JMenuItem("Sobre a Aplicacao") );

		novoClienteItem.addActionListener(this);
        alterarClienteItem.addActionListener(this); // <-- Adiciona isto
        eliminarClienteItem.addActionListener(this); // <-- E isto
        sairItem.addActionListener(this);   
	
		
	}
	
	public void actionPerformed(ActionEvent evt) {
        painelConteudo.removeAll(); // Limpa a área branca

        if (evt.getSource() == novoClienteItem) {
            painelConteudo.add(new NovoCliente(), BorderLayout.CENTER);
        } else if (evt.getSource() == alterarClienteItem) {
            painelConteudo.add(new AlterarCliente(), BorderLayout.CENTER); // substitui JLabel por painel real
        } else if (evt.getSource() == eliminarClienteItem) {
            painelConteudo.add(new EliminarCliente(), BorderLayout.CENTER); // substitui JLabel por painel real
        } else if (evt.getSource() == sairItem) {
            dispose();
            return;
        }

        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

	public static void main(String[] args)
	{
		new MenuPrincipal();
	}
}
