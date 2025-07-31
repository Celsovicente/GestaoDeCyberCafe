/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: ClienteVisao.java
Data: 30.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ClienteVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public ClienteVisao(boolean alterar, ClienteModelo modelo)
    {
        super("Registrar Clientes");

        editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(450, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, nomeJTF, telefoneJTF, custoJTF;
        private JComboBox generoJCB, provinciaJCB, municipioJCB, comunaJCB, horarioInicioJCB, 
        horarioFimJCB, estadoJCB;
        private String[] generoArray = {"Masculino", "Feminino"}; 
        private String[] status = {"Ativo", "Inativo"};
        private JComboBoxTabela3_Tabela3 provinciaComMunicipio;
        private JComboBoxTabela2_Tabela3 horario;
        private ClienteFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(11, 2));
            provinciaComMunicipio = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
            horario = new JComboBoxTabela2_Tabela3("HoraInicio.tab", "HoraFim.tab");
            file = new ClienteFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());

            // 3º linha
            add(new JLabel("Telefone"));
            add(telefoneJTF = new JTextField());

            // 4º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox(generoArray));

             // 5º linha
            add(new JLabel("Provincia"));
            add(provinciaJCB = provinciaComMunicipio.getComboBoxFather());

            // 6º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = provinciaComMunicipio.getComboBoxSun());

            // 7º liha
            add(new JLabel("Comuna"));
            add(comunaJCB = provinciaComMunicipio.getComboBoxNeto());

            // 8º linha
            add(new JLabel("Hora de Inicio"));
            add(horarioInicioJCB = horario.getComboBoxFather());

            // 9º linha 
            add(new JLabel("Hora de Termino"));
            add(horarioFimJCB = horario.getComboBoxSun());

            // 10º linha
            add(new JLabel("Custo"));
            add(custoJTF = new JTextField());

            // 11º linha
            add(new JLabel("Estado"));
            add(estadoJCB = new JComboBox(status));
        }

        // construtor com parametros
        public PainelCentro(ClienteModelo modelo)
        {
            setLayout(new GridLayout(11, 2));
            provinciaComMunicipio = new JComboBoxTabela3_Tabela3("Provincias.tab", "Municipios.tab", "Comunas.tab");
            horario = new JComboBoxTabela2_Tabela3("HoraInicio.tab", "HoraFim.tab");
            file = new ClienteFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome"));
            add(nomeJTF = new JTextField());
            nomeJTF.setText(modelo.getTelefone());

            // 3º linha
            add(new JLabel("Telefone"));
            add(telefoneJTF = new JTextField());
            telefoneJTF.setText(modelo.getTelefone());

            // 4º linha
            add(new JLabel("Genero"));
            add(generoJCB = new JComboBox(generoArray));
            generoJCB.setSelectedItem(modelo.getGenero());

             // 5º linha
            add(new JLabel("Provincia"));
            add(provinciaJCB = provinciaComMunicipio.getComboBoxFather());
            provinciaJCB.setSelectedItem(modelo.getProvincia());

            // 6º linha
            add(new JLabel("Municipio"));
            add(municipioJCB = provinciaComMunicipio.getComboBoxSun());
            municipioJCB.setSelectedItem(modelo.getMunicipio());

            // 7º liha
            add(new JLabel("Comuna"));
            add(comunaJCB = provinciaComMunicipio.getComboBoxNeto());
            comunaJCB.setSelectedItem(modelo.getComuna());

            // 8º linha
            add(new JLabel("Hora de Inicio"));
            add(horarioInicioJCB = horario.getComboBoxFather());
            horarioInicioJCB.setSelectedItem(modelo.getHorarioInicio());


            // 9º linha 
            add(new JLabel("Hora de Termino"));
            add(horarioFimJCB = horario.getComboBoxSun());
            horarioFimJCB.setSelectedItem(modelo.getHorarioTermino());

            // 10º linha
            add(new JLabel("Custo"));
            add(custoJTF = new JTextField());
            custoJTF.setText("" + modelo.getCusto());

            // 11º linha
            add(new JLabel("Estado"));
            add(estadoJCB = new JComboBox(status));
            estadoJCB.setSelectedItem(modelo.getEstado());
        }
 
        // getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getNome()
        {
            return nomeJTF.getText();
        }

        public String getTelefone()
        {
            return telefoneJTF.getText().trim();
        }

        public String getGenero()
        {
            return String.valueOf(generoJCB.getSelectedItem());
        }

        public String getProvincia()
        {
            return String.valueOf(provinciaJCB.getSelectedItem());
        }

        public String getMunicipio()
        {
            return String.valueOf(municipioJCB.getSelectedItem());
        }

        public String getComuna()
        {
            return String.valueOf(comunaJCB.getSelectedItem());
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

        public String getEstado()
        {
            return String.valueOf(estadoJCB.getSelectedItem());
        }

        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" +id);
        }

        public void setNome(String nome)
        {
            nomeJTF.setText(nome);
        }

        public void setTelefone(String telefone)
        {
            telefoneJTF.setText(telefone);
        }

        public void setGenero(String genero)
        {
            generoJCB.setSelectedItem(genero);
        }

        public void setProvincia(String provincia)
        {
            provinciaJCB.setSelectedItem(provincia);
        }

        public void setMunicipio(String municipio)
        {
            municipioJCB.setSelectedItem(municipio);
        }

        public void setComuna(String comuna)
        {
            comunaJCB.setSelectedItem(comuna);
        }

        public void setHorarioInicio(String horarioInicio)
        {
            horarioInicioJCB.setSelectedItem(horarioInicio);
        }

        public void setHorarioTermino(String horarioFim)
        {
            horarioFimJCB.setSelectedItem(horarioFim);
        }

        public void setCusto(float custo)
        {
            custoJTF.setText("" + custo);
        }

        public void setEstado(String estado)
        {
            estadoJCB.setSelectedItem(estado);
        }
        // metodo para validar os campos
        public boolean validarDados()
        {
            if (getNome().isEmpty() || getTelefone().isEmpty() || getGenero().isEmpty() ||
                getProvincia().isEmpty() || getMunicipio().isEmpty() || getComuna().isEmpty()
                || getHorarioInicio().isEmpty() || getHorarioTermino().isEmpty() ||
                 getEstado().isEmpty()) 
                {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                if(getCusto() < 0)
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
                ClienteModelo modelo = new ClienteModelo(
                getId(),
                getNome(),
                getTelefone(),
                getGenero(),
                getProvincia(),
                getMunicipio(),
                getComuna(),
                getHorarioInicio(),
                getHorarioTermino(),
                getCusto(),
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
                ClienteModelo modelo = new ClienteModelo(
                getId(),
                getNome(),
                getTelefone(),
                getGenero(),
                getProvincia(),
                getMunicipio(),
                getComuna(),
                getHorarioInicio(),
                getHorarioTermino(),
                getCusto(),
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
        new ClienteVisao(false, new ClienteModelo());
    }
}