/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: ConfiguracaoVisao.java
Data: 31.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ConfiguracaoVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public ConfiguracaoVisao(boolean alterar, ConfiguracaoModelo modelo)
    {
        super("Registrar Configuracao");

        editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(350, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, valorJTF, totalEstacoesJTF;
        private ConfiguracaoFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(3, 2));
            file = new ConfiguracaoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Valor"));
            add(valorJTF = new JTextField());

            // 3º linha
            add(new JLabel("Total de Estacoes"));
            add(totalEstacoesJTF = new JTextField());
        }

        // construtor com parametros
        public PainelCentro(ConfiguracaoModelo modelo)
        {
            setLayout(new GridLayout(3, 2));
            file = new ConfiguracaoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Valor"));
            add(valorJTF = new JTextField());
            valorJTF.setText("" + modelo.getValorPorHora());

            // 3º linha
            add(new JLabel("Total de Estacoes"));
            add(totalEstacoesJTF = new JTextField());
            totalEstacoesJTF.setText("" + modelo.getTotalEstacoes());
        }
 
        // getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public float getValorPorHora()
        {
            return Float.parseFloat(valorJTF.getText().trim());
        }

        public int getTotalEstacoes()
        {
            return Integer.parseInt(totalEstacoesJTF.getText().trim());
        }
        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" +id);
        }

        public void setValorPorHora(float valorPorHora)
        {
            valorJTF.setText("" + valorPorHora);
        }

        public void setTotalEstacoes(int totalEstacoes)
        {
            totalEstacoesJTF.setText("" + totalEstacoes);
        }

        // metodo para validar os campos
        public boolean validarDados()
        {
            if(valorJTF.getText().trim().isEmpty() || totalEstacoesJTF.getText().trim().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            return true;
        }

        // metodo salvar
        public void salvar()
        {
            if(validarDados())
            {
                ConfiguracaoModelo modelo = new ConfiguracaoModelo(
                getId(),
                getValorPorHora(), 
                getTotalEstacoes(),
                true);

                JOptionPane.showMessageDialog(null, modelo.toString());
                modelo.salvar();
                dispose();
            }
            
        }

        // alterar
        public void alterar()
        {
            if(validarDados())
            {            
                ConfiguracaoModelo modelo = new ConfiguracaoModelo(
                getId(),
                getValorPorHora(), 
                getTotalEstacoes(),
                true);

                JOptionPane.showMessageDialog(null, modelo.toString());
                modelo.salvarDados();
                dispose();
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton salvarJBT, cancelarJBT;
        
        public PainelSul()
        {
           setLayout(new FlowLayout());

            add(salvarJBT = new JButton("Salvar", new ImageIcon("image/save24.png")));
            add(cancelarJBT = new JButton("Cancelar", new ImageIcon("image/cancel24.png")));

            salvarJBT.setBackground(Color.GREEN);
            cancelarJBT.setBackground(Color.RED);

            salvarJBT.setForeground(Color.WHITE);
            cancelarJBT.setForeground(Color.WHITE);
            
            salvarJBT.addActionListener(this);
            cancelarJBT.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == salvarJBT)
            {
                if(editar)
                    centro.alterar();
                else    
                    centro.salvar();
            }
            else
                dispose();
        }
    }

    public void definirTema() 
    {
        try 
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
                {
                    if ("Nimbus".equals(info.getName())) 
                    {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {
        }
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new ConfiguracaoVisao(false, new ConfiguracaoModelo());
    }
}