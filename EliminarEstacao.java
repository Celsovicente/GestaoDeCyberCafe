/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: EliminarEstacao.java
Data: 31.07.2025
***********************************/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarEstacao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EliminarEstacao()
    {
        super("Pesquisas das Estacoes Para Eliminacao");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField idJTF;
        private JComboBox estadoJCB;
        private JRadioButton pesquisarPorId, pesquisarPorEstado;
        private String[] estado = {"Livre", "Ocupado"};
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorId = new JRadioButton("Pesquisa Por Id"));
            add(pesquisarPorEstado = new JRadioButton("Pesquisa Por Estado"));

            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorEstado);
            
            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);
            
            add(new JLabel("Escolha o Estado Procurado"));
            add(estadoJCB = new JComboBox(estado));
            estadoJCB.setEnabled(false);
            
            pesquisarPorId.addActionListener(this);
            pesquisarPorEstado.addActionListener(this);
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getEstadoProcurado()
        {
            return String.valueOf(estadoJCB.getSelectedItem());
        }

        public int getTipoPesquisa()
        {
            if(pesquisarPorId.isSelected())
                return 1;
            else 
                return 2;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarPorId)
            {
                idJTF.setEnabled(true);
                estadoJCB.setEnabled(false); 
            }
            else if(event.getSource() == pesquisarPorEstado)
            {
                idJTF.setEnabled(false);
                estadoJCB.setEnabled(true);
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
                EstacaoModelo modelo;
                if(centro.getTipoPesquisa() == 1)
                {
                    modelo = EstacaoFile.getPesquisaPorId(centro.getIdProcurado());
                    
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new EstacaoFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador"); 
                }
                else if(centro.getTipoPesquisa() == 2)
                {
                    modelo = EstacaoFile.getPesquisaPorEstado(centro.getEstadoProcurado());
                    
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new EstacaoFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador"); 
                }
            }
            else 
                dispose();
        }
    }
}
