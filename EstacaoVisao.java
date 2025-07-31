/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: EstacaoVisao.java
Data: 31.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class EstacaoVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public EstacaoVisao(boolean alterar, EstacaoModelo modelo)
    {
        super("Registrar Estacao");

        editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(350, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF; 
        private JComboBox estadoJCB;
        private String[] estado = {"Livre", "Ocupada"};
        private EstacaoFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(2, 2));
            file = new EstacaoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Estado"));
            add(estadoJCB = new JComboBox(estado));
        }

        // construtor com parametros
        public PainelCentro(EstacaoModelo modelo)
        {
            setLayout(new GridLayout(2, 2));
            file = new EstacaoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Estado"));
            add(estadoJCB = new JComboBox(estado));
            estadoJCB.setSelectedItem(modelo.getEstado());
        }
 
        // getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getEstado()
        {
            return String.valueOf(estadoJCB.getSelectedItem());
        }

        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" +id);
        }

        public void setEstado(String estado)
        {
            estadoJCB.setSelectedItem(estado);
        }

        // metodo para validar os campos
        public boolean validarDados()
        {
            if(getEstado().isEmpty()) 
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
                EstacaoModelo modelo = new EstacaoModelo(
                getId(),
                getEstado(),
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
                EstacaoModelo modelo = new EstacaoModelo(
                getId(),
                getEstado(),
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
        new EstacaoVisao(false, new EstacaoModelo());
    }
}