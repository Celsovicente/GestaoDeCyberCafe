/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: ConfiguracaoModelo.java
Data: 31.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ConfiguracaoModelo implements RegistGeneric
{
    private int id, totalEstacoes;
    private float valorPorHora;
    private boolean status;

    public ConfiguracaoModelo()
    {
        id = 0;
        valorPorHora = 0;
        totalEstacoes = 0;
        status = false;
    }

    public ConfiguracaoModelo(int id, float valorPorHora, int totalEstacoes, boolean status)
    {
        this.id = id;
        this.valorPorHora = valorPorHora;
        this.totalEstacoes = totalEstacoes;
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public float getValorPorHora()
    {
        return valorPorHora;
    }

    public int getTotalEstacoes()
    {
        return totalEstacoes;
    }

    public boolean getStatus()
    {
        return status;
    }
 
    // metodos setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setValorPorHora(int valorPorHora)
    {
        this.valorPorHora = valorPorHora;
    }

    public void setTotalEstacoes(int totalEstacoes)
    {
        this.totalEstacoes = totalEstacoes;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    // metodo toString
    public String toString()
    {
        String dados = "Dados da Configuracao\n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Valor Por Hora: " + getValorPorHora() + "\n";
        dados += "Total de Estacoes: " + getTotalEstacoes() + "\n";
        dados += "Status: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 4 * 3 + 1;
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    // metodo write
    public void write(RandomAccessFile stream)
    {
        try
        {
            stream.writeInt(id);            
            stream.writeFloat(valorPorHora);
            stream.writeInt(totalEstacoes);
            stream.writeBoolean(status);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao escrever no Ficheiro");
        }
    }

    // metodo read
    public void read(RandomAccessFile stream)
    {
        try
        {
            id = stream.readInt();
            valorPorHora = stream.readFloat();
            totalEstacoes = stream.readInt();
            status = stream.readBoolean();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    public void salvar()
    {
        ConfiguracaoFile file = new ConfiguracaoFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        ConfiguracaoFile file = new ConfiguracaoFile();
        file.alterarDados(this);
    }
}