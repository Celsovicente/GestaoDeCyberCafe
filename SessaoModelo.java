/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: SessaoModelo.java
Data: 31.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class SessaoModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo horarioInicio, horarioFim;
    private float custo;
    private DataModelo dataSessao;
    private boolean status;

    public SessaoModelo()
    {
        id = 0;
        horarioInicio = new StringBufferModelo("", 20);
        horarioFim = new StringBufferModelo("", 20);
        custo = 0;
        dataSessao = new DataModelo();
        status = false;
    }

    public SessaoModelo(int id, String horarioInicio, String horarioFim, float custo,
    String dataSessao, boolean status)
    {
        this.id = id;
        this.horarioInicio = new StringBufferModelo(horarioInicio, 20);
        this.horarioFim = new StringBufferModelo(horarioFim, 20);
        this.custo = custo;
        this.dataSessao = new DataModelo(dataSessao);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getHorarioInicio()
    {
        return horarioInicio.toStringEliminatingSpaces();
    }

    public String getHorarioTermino()
    {
        return horarioFim.toStringEliminatingSpaces();
    }

    public float getCusto()
    {
        return custo;
    }

    public String getDataSessao()
    {
        return dataSessao.toString();
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

    public void setHorarioInicio(String horarioInicio)
    {
        this.horarioInicio = new StringBufferModelo(horarioInicio, 20);
    }

    public void setHorarioTermino(String horarioFim)
    {
        this.horarioFim = new StringBufferModelo(horarioFim, 20);
    }

    public void setCusto(float custo)
    {
        this.custo = custo;
    }

    public void setDataSessao(String data)
    {
        this.dataSessao = new DataModelo(data);
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    // metodo toString
    public String toString()
    {
        String dados = "Dados da Sessao\n\n";
        dados += "Id: " + getId() + "\n";
        dados += "Horario de Inicio: " + getHorarioInicio() + "\n";
        dados += "Horario de Termino: " + getHorarioTermino() + "\n";
        dados += "Custo: " + getCusto() + "\n";
        dados += "Data de Sessao: " + getDataSessao() + "\n";
        dados += "Status: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 40 * 2 + 4 * 2 + 12 + 1;
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
            horarioInicio.write(stream);            
            horarioFim.write(stream);
            stream.writeFloat(custo);
            dataSessao.write(stream);
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
            horarioInicio.read(stream);            
            horarioFim.read(stream);
            custo = stream.readFloat();
            dataSessao.read(stream);
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
        SessaoFile file = new SessaoFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        SessaoFile file = new SessaoFile();
        file.alterarDados(this);
    }
}