/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: PesquisarConfiguracao.java
Data: 31.07.2025
***********************************/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class PesquisarConfiguracao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public PesquisarConfiguracao()
    {
        super("Pesquisas das Configuracoes");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField idJTF, valorJTF;
        private JRadioButton pesquisarPorId, pesquisarPorValor;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorId = new JRadioButton("Pesquisa Por Id"));
            add(pesquisarPorValor = new JRadioButton("Pesquisa Por Valor"));

            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorValor);
            
            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);
            
            add(new JLabel("Digite o Valor Procurado"));
            add(valorJTF = new JTextField());
            valorJTF.setEnabled(false);
            
            pesquisarPorId.addActionListener(this);
            pesquisarPorValor.addActionListener(this);
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public float getValorProcurado()
        {
            return Float.parseFloat(valorJTF.getText().trim());
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
                valorJTF.setEnabled(false); 
            }
            else if(event.getSource() == pesquisarPorValor)
            {
                idJTF.setEnabled(false);
                valorJTF.setEnabled(true);
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
                if(centro.getTipoPesquisa() == 1)
                    ConfiguracaoFile.pesquisarPorId(centro.getIdProcurado());
                else if(centro.getTipoPesquisa() == 2)
                    ConfiguracaoFile.pesquisarPorValor(centro.getValorProcurado());
            }
            else 
                dispose();
        }
    }
}
