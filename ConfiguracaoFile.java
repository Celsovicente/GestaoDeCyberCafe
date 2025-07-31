/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: ConfiguracaoFile.java
Data: 31.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ConfiguracaoFile extends ObjectsFile
{
    public ConfiguracaoFile()
    {
        super("ConfiguracaoFile.dat", new ConfiguracaoModelo());
    }  

    public void salvarDados(ConfiguracaoModelo modelo)
    {
        try
        {
            // colocar o file pointer no final do ficheiro
            stream.seek(stream.length());

            // escrever no modelo
            modelo.write(stream);

            incrementarProximoCodigo();
            JOptionPane.showMessageDialog(null,  "Dados Salvos com Sucessso");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha ao Salvar os Dados");
        }
    }

    public void alterarDados(ConfiguracaoModelo modelo_novo)
	{
		ConfiguracaoModelo modelo_antigo = new ConfiguracaoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write( stream);
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public void eliminarDados(ConfiguracaoModelo modelo_novo)
	{
		ConfiguracaoModelo modelo_antigo = new ConfiguracaoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write(stream);
						return;
					}							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public static void listarConfiguracoes()
    {
        ConfiguracaoFile file = new ConfiguracaoFile();
        ConfiguracaoModelo modelo = new ConfiguracaoModelo();

        String[] colunas = 
        {
            "Id", "Valor Por Hora", "Total de Estacoes", "Status"
        };

        java.util.List<Object[]> linhas = new java.util.ArrayList<>();

        try {
            file.stream.seek(4);

            for (int i = 0; i < file.getNregistos(); i++) 
            {
                modelo.read(file.stream);

                if (modelo.getStatus()) {
                    Object[] linha = {
                        modelo.getId(),
                        modelo.getValorPorHora(),
                        modelo.getTotalEstacoes(),
                        modelo.getStatus()                    
                        };
                    linhas.add(linha);
                }
            }

            Object[][] dados = new Object[linhas.size()][colunas.length];
            for (int i = 0; i < linhas.size(); i++) 
            {
                dados[i] = linhas.get(i);
            }

            JTable tabela = new JTable(dados, colunas);
            tabela.setFillsViewportHeight(true);

            // ESSENCIAL: desativa redimensionamento automático para permitir scroll horizontal
            tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            // Você pode definir larguras mínimas para cada coluna se quiser
            for (int i = 0; i < colunas.length; i++) {
                tabela.getColumnModel().getColumn(i).setPreferredWidth(150);
            }

            JScrollPane scroll = new JScrollPane(tabela,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JDialog dialogo = new JDialog();
            dialogo.setTitle("Gestao de Um Cyber Cafe");
            dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialogo.setLayout(new BorderLayout());
            dialogo.add(scroll, BorderLayout.CENTER);
            dialogo.setSize(600, 500); 
            dialogo.setLocationRelativeTo(null);
            dialogo.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int pesquisarPorId(int idProcurado)
    {
        ConfiguracaoFile file = new ConfiguracaoFile();
        ConfiguracaoModelo modelo = new ConfiguracaoModelo();
        boolean estado = false;

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getId() == idProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    estado = true;
                    return 0;
                }
            }
            if(!estado)
            {
               JOptionPane.showMessageDialog(null, "Erro, id nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return idProcurado;
    }

    public static float pesquisarPorValor(float valorProcurado)
    {
        ConfiguracaoFile file = new ConfiguracaoFile();
        ConfiguracaoModelo modelo = new ConfiguracaoModelo();
        boolean estado = false;
        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getValorPorHora() == valorProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    estado = true;
                    return 0;
                }
            }
            if(!estado)
            {
               JOptionPane.showMessageDialog(null, "Erro, valor nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return valorProcurado;
    }

    // metodos para a edicao
    public static ConfiguracaoModelo getPesquisaPorId(int idProcurado)
    {
        ConfiguracaoFile file = new ConfiguracaoFile();
        ConfiguracaoModelo modelo = new ConfiguracaoModelo();
        boolean estado = false;

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getId() == idProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    estado = true;
                    return modelo;
                }
            }
            if(!estado)
            {
               JOptionPane.showMessageDialog(null, "Erro, id nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static ConfiguracaoModelo getPesquisaPorValor(float valorProcurado)
    {
        ConfiguracaoFile file = new ConfiguracaoFile();
        ConfiguracaoModelo modelo = new ConfiguracaoModelo();
        boolean estado = false;

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getValorPorHora() == valorProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    estado = true;
                    return modelo;
                }
            }
            if(!estado)
            {
                JOptionPane.showMessageDialog(null, "Erro, valor nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

}   