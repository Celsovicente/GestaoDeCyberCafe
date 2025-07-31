/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: EditarCliente.java
Data: 30.07.2025
***********************************/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarCliente extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EditarCliente()
    {
        super("Pesquisas dos Clientes para Edicao");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JComboBox nomeJCB, telefoneJCB;
        private JRadioButton pesquisarPorNome, pesquisarPorTelefone;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorNome = new JRadioButton("Pesquisa Por Nome"));
            add(pesquisarPorTelefone = new JRadioButton("Pesquisa Por Telefone"));

            grupo.add(pesquisarPorNome);
            grupo.add(pesquisarPorTelefone);
            
            add(new JLabel("Escolha o nome Procurado"));
            add(nomeJCB = new JComboBox(ClienteFile.getAllNames()));
            nomeJCB.setEnabled(false);
            
            add(new JLabel("Escolha o telefone Procurado"));
            add(telefoneJCB = new JComboBox(ClienteFile.getAllTelefone()));
            telefoneJCB.setEnabled(false);
            
            pesquisarPorNome.addActionListener(this);
            pesquisarPorTelefone.addActionListener(this);
        }

        public String getNomeProcurado() 
        {
            return String.valueOf(nomeJCB.getSelectedItem());
        }

        public String getTelefoneProcurado()
        {
            return String.valueOf(telefoneJCB.getSelectedItem());
        }

        public int getTipoPesquisa()
        {
            if(pesquisarPorNome.isSelected())
                return 1;
            else 
                return 2;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarPorNome)
            {
                nomeJCB.setEnabled(true);
               telefoneJCB.setEnabled(false); 
            }
            else if(event.getSource() == pesquisarPorTelefone)
            {
                nomeJCB.setEnabled(false);
                telefoneJCB.setEnabled(true);
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton pesquisarJB, cancelarJB;

        public PainelSul()
        {
            add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("image/search32.PNG")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.PNG")));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarJB)
            {    
                ClienteModelo modelo;
                if(centro.getTipoPesquisa() == 1)
                {   
                    modelo = ClienteFile.getPesquisaPorNome(centro.getNomeProcurado());
                    new ClienteVisao(true, modelo);
                }   
                else if(centro.getTipoPesquisa() == 2)
                {
                    modelo = ClienteFile.getPesquisaPorTelefone(centro.getTelefoneProcurado());
                    new ClienteVisao(true, modelo);
                }
            }
            else 
                dispose();
        }
    }
}
