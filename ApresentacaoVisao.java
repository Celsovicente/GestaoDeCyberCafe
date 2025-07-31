/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: ApresentacaoVisao.java
Data: 30.07.2025
***********************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import SwingComponents.*;
import Calendario.*;

public class ApresentacaoVisao extends JFrame 
{
    
    private PainelCentro centro;
    private PainelSul sul;

    public ApresentacaoVisao()
    {
        super("Tela de Boas Vindas");

        JPanel painelNorte = new JPanel();

        ImageIcon iconOriginal = new ImageIcon("image/apresentacao.png");
        Image imagemRedimensionada = iconOriginal.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon iconRedimensionado = new ImageIcon(imagemRedimensionada);

        JLabel lbImagem = new JLabel(iconRedimensionado);

        painelNorte.add(lbImagem);
        getContentPane().add(painelNorte, BorderLayout.NORTH);

        getContentPane().add(painelNorte , BorderLayout.NORTH);
        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
        
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {
            JTextArea areaPrincipal;
            JCheckBox concordarJCB;
            public PainelCentro()
            {
                setLayout(new GridLayout(2, 1));
                
                add(new JScrollPane(areaPrincipal = new JTextArea(80 , 60)));
                areaPrincipal.setFocusable(false);
                areaPrincipal.setText(
                "Bem-vindo ao Sistema de Gestao de CyberCafe.\n\n" +
                "\tTema: Gestao de CyberCafe\n\n" +
                "Este projeto tem como objetivo oferecer um ambiente onde os clientes\n" +
                "possam acessar computaores e a internet mediante o pagamento de uma taxa.\n\n" +
                "O sistema permite o cadastro de clientes, o registo de acessos,\n" +
                "o controlo de tempo de utilizacao e o acompanhamento dos pagamentos efetuados.\n\n" +
                "Foi desenvolvido no ambito da cadeira de Fundamentos de Programacao 2,\n" +
                "no curso de Engenharia Informatica da UCAN. Destina-se ao uso exclusivo do pessoal autorizado.\n\n" +
                "O sistema visa facilitar o controlo e a gestao dos servicos prestados pelo cyber cafe,\n" +
                "permitindo localizar informacoes de forma rapida, organizada e segura.\n\n" +
                "Desenvolvido por Tinilsa Domingos, estudante do 1º ano, ID: 3433429870.\n\n" +
                "Se concorda com os termos e condicoes, clique em 'Concordar' para continuar.");

                add(concordarJCB = new JCheckBox("Concordo com os termos a seguir"));

                concordarJCB.addActionListener(this);
            }

            public void actionPerformed(ActionEvent event)
            {
                if(event.getSource() == concordarJCB)
                    if(concordarJCB.isSelected())
                        sul.ativarBotao(true);
                    else
                        sul.ativarBotao(false);
            }
    }

    
    class PainelSul extends JPanel implements ActionListener
    {
        JButton entrarJB, sairJB;
        public PainelSul()
        {
            add(entrarJB = new JButton("Entrar", new ImageIcon("image/next24.png")));
            add(sairJB = new JButton("Sair", new ImageIcon("image/logout24.png")));

            ativarBotao( false );

            entrarJB.addActionListener(this);
            sairJB.addActionListener(this);
        }

        public void ativarBotao(boolean status)
        {
            entrarJB.setEnabled(status);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == entrarJB)
            {
                dispose();
                new LoginVisao();
            }
            else
                dispose();
        }
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new ApresentacaoVisao();       
    }
}