/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: SessaoVisao.java
Data: 31.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class SessaoVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public SessaoVisao(boolean alterar, SessaoModelo modelo)
    {
        super("Registrar Sessao");

        editar = alterar;

        definirTema();
        if(!alterar)
        {
            getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, custoJTF, dataSessaoJTF;
        private JComboBox horarioInicioJCB, horarioFimJCB;
        private JComboBoxTabela2_Tabela3 horario;
        private JTextFieldData txtData;
        private SessaoFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(5, 2));
            horario = new JComboBoxTabela2_Tabela3("HoraInicio.tab", "HoraFim.tab");
            file = new SessaoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Horario de Inicio"));
            add(horarioInicioJCB = horario.getComboBoxFather());

            // 3º linha 
            add(new JLabel("Hora de Termino"));
            add(horarioFimJCB = horario.getComboBoxSun());

            // 4º linha
            add(new JLabel("Custo"));
            add(custoJTF = new JTextField());

            // 5º linha
            add(new JLabel("Data de Sessao"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
        }

        // construtor com parametros
        public PainelCentro(SessaoModelo modelo)
        {
            setLayout(new GridLayout(5, 2));
            horario = new JComboBoxTabela2_Tabela3("HoraInicio.tab", "HoraFim.tab");
            file = new SessaoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Horario de Inicio"));
            add(horarioInicioJCB = horario.getComboBoxFather());
            horarioInicioJCB.setSelectedItem(modelo.getHorarioInicio());

            // 3º linha 
            add(new JLabel("Hora de Termino"));
            add(horarioFimJCB = horario.getComboBoxSun());
            horarioFimJCB.setSelectedItem(modelo.getHorarioTermino());

            // 4º linha
            add(new JLabel("Custo"));
            add(custoJTF = new JTextField());
            custoJTF.setText("" + modelo.getCusto());

            // 5º linha
            add(new JLabel("Data de Sessao"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataSessao());
        }
 
        // getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getHorarioInicio()
        {
            return String.valueOf(horarioInicioJCB.getSelectedItem());
        }

        public String getHorarioTermino()
        {
            return String.valueOf(horarioFimJCB.getSelectedItem());
        }

        public float getCusto()
        {
            return Float.parseFloat(custoJTF.getText().trim());
        }

        public String getDataSessao()
        {
            return txtData.getDTestField().getText();
        }

        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" +id);
        }

        public void setHorarioInicio(String horarioInicio)
        {
            horarioInicioJCB.setSelectedItem(horarioInicio);
        }

        public void setHorarioTermino(String horarioFim)
        {
            horarioFimJCB.setSelectedItem(horarioFim);
        }

        public void setCusto(int custo)
        {
            custoJTF.setText("" + custo);
        }

        public void setDataSessao(String data)
        {
            txtData.getDTestField().setText(data);
        }

        // metodo para validar os campos
        public boolean validarDados()
        {
            if(getHorarioInicio().isEmpty() || getHorarioTermino().isEmpty() || 
                custoJTF.getText().trim().isEmpty() || getDataSessao().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            if(getCusto() <= 0)
            {
                JOptionPane.showMessageDialog(null, "O custo deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }

        // metodo salvar
        public void salvar()
        {
            if(validarDados())
            {
                SessaoModelo modelo = new SessaoModelo(
                getId(),
                getHorarioInicio(),
                getHorarioTermino(),
                getCusto(),
                getDataSessao(),
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
                SessaoModelo modelo = new SessaoModelo(
                getId(),
                getHorarioInicio(),
                getHorarioTermino(),
                getCusto(),
                getDataSessao(),
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
        new SessaoVisao(false, new SessaoModelo());
    }
}