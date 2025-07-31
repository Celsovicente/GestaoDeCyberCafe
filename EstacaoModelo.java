/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: EstacaoModelo.java
Data: 31.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class EstacaoModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo estado;
    private boolean status;

    public EstacaoModelo()
    {
        id = 0;
        estado = new StringBufferModelo("", 20);
        status = false;
    }

    public EstacaoModelo(int id, String estado, boolean status)
    {
        this.id = id;
        this.estado = new StringBufferModelo(estado, 20);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getEstado()
    {
        return estado.toStringEliminatingSpaces();
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

    public void setEstado(String estado)
    {
        this.estado = new StringBufferModelo(estado, 20);
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    // metodo toString
    public String toString()
    {
        String dados = "Dados da Estacao:\n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Estado: " + getEstado() + "\n";
        dados += "Status: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 20 * 2 + 4 + 1;
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
            estado.write(stream);
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
            id = stream.readInt();;
            estado.read(stream);
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
        EstacaoFile file = new EstacaoFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        EstacaoFile file = new EstacaoFile();
        file.alterarDados(this);
    }
}