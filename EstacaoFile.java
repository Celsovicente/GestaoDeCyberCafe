/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: EstacaoFile.java
Data: 31.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class EstacaoFile extends ObjectsFile
{
    public EstacaoFile()
    {
        super("EstacaoFile.dat", new EstacaoModelo());
    }  

    public void salvarDados(EstacaoModelo modelo)
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

    public void alterarDados(EstacaoModelo modelo_novo)
	{
		EstacaoModelo modelo_antigo = new EstacaoModelo();
		
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

    public void eliminarDados(EstacaoModelo modelo_novo)
	{
		EstacaoModelo modelo_antigo = new EstacaoModelo();
		
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

    public static void listarEstacao()
    {
        EstacaoFile file = new EstacaoFile();
        EstacaoModelo modelo = new EstacaoModelo();

        String[] colunas = 
        {
            "Id", "Estado", "Status"
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
                        modelo.getEstado(),
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
            dialogo.setSize(480, 500); 
            dialogo.setLocationRelativeTo(null);
            dialogo.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int pesquisarPorId(int idProcurado)
    {
        EstacaoFile file = new EstacaoFile();
        EstacaoModelo modelo = new EstacaoModelo();
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

    public static String pesquisarPorEstado(String estadoProcurado)
    {
        EstacaoFile file = new EstacaoFile();
        EstacaoModelo modelo = new EstacaoModelo();
        boolean estado = false;

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getEstado().equalsIgnoreCase(estadoProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    estado = true;
                    return "";
                }
            }
            if(!estado)
            {
               JOptionPane.showMessageDialog(null, "Erro, estado nao encontrado", 
                "File Not Found", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return estadoProcurado;
    }

    // metodos para a edicao
    public static EstacaoModelo getPesquisaPorId(int idProcurado)
    {
        EstacaoFile file = new EstacaoFile();
        EstacaoModelo modelo = new EstacaoModelo();
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

    public static EstacaoModelo getPesquisaPorEstado(String estadoProcurado)
    {
        EstacaoFile file = new EstacaoFile();
        EstacaoModelo modelo = new EstacaoModelo();
        boolean estado = false;

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getEstado().equalsIgnoreCase(estadoProcurado) && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    estado = true;
                    return modelo;
                }
            }
            if(!estado)
            {
                JOptionPane.showMessageDialog(null, "Erro, estado nao encontrado", 
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