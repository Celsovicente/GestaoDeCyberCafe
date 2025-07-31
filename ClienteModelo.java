/**********************************
Tema: Gestao de um cyber Cafe
Nome: Tinilsa Domingos
Número: 34298
Ficheiro: ClienteModelo.java
Data: 30.07.2025
***********************************/ 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ClienteModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo nome, telefone, genero, provincia, municipio, comuna, horarioInicio, 
    horarioFim, estado;
    private float custo;
    private boolean status;

    public ClienteModelo()
    {
        id = 0;
        nome = new StringBufferModelo("", 50);
        telefone = new StringBufferModelo("", 20);
        genero = new StringBufferModelo("", 20);
        provincia = new StringBufferModelo("", 20);
        municipio = new StringBufferModelo("", 20);
        comuna = new StringBufferModelo("", 20);
        horarioInicio = new StringBufferModelo("", 20);
        horarioFim = new StringBufferModelo("", 20);
        custo = 0;
        estado = new StringBufferModelo("", 20);
        status = false;
    }

    public ClienteModelo(int id, String nome, String telefone, String genero, String provincia,
    String municipio, String comuna, String horarioInicio, String horarioFim, float custo, String estado,
    boolean status)
    {
        this.id = id;
        this.nome = new StringBufferModelo(nome, 50);
        this.telefone = new StringBufferModelo(telefone, 20);
        this.genero = new StringBufferModelo(genero, 20);
        this.provincia = new StringBufferModelo(provincia, 20);
        this.municipio = new StringBufferModelo(municipio, 20);
        this.comuna = new StringBufferModelo(comuna, 20);
        this.horarioInicio = new StringBufferModelo(horarioInicio, 20);
        this.horarioFim = new StringBufferModelo(horarioFim, 20);
        this.custo = custo;
        this.estado = new StringBufferModelo(estado, 20);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome.toStringEliminatingSpaces();
    }

    public String getTelefone()
    {
        return telefone.toStringEliminatingSpaces();
    }

    public String getGenero()
    {
        return genero.toStringEliminatingSpaces();
    }

    public String getProvincia()
    {
        return provincia.toStringEliminatingSpaces();
    }

    public String getMunicipio()
    {
        return municipio.toStringEliminatingSpaces();
    }

    public String getComuna()
    {
        return comuna.toStringEliminatingSpaces();
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

    public void setNome(String nome)
    {
        this.nome = new StringBufferModelo(nome, 50);
    }

    public void setTelefone(String telefone)
    {
        this.telefone = new StringBufferModelo(telefone, 20);
    }

    public void setGenero(String genero)
    {
        this.genero = new StringBufferModelo(genero, 20);
    }

    public void setProvincia(String provincia)
    {
        this.provincia = new StringBufferModelo(provincia, 20);
    }

    public void setMunicipio(String municipio)
    {
        this.municipio = new StringBufferModelo(municipio, 20);
    }

    public void setComuna(String comuna)
    {
        this.comuna = new StringBufferModelo(comuna, 20);
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
        String dados = "Dados do Cliente\n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Nome: " + getNome() + "\n";
        dados += "Telefone: " + getTelefone() + "\n";
        dados += "Genero: " + getGenero() + "\n";
        dados += "Provincia: " + getProvincia() + "\n";
        dados += "Municipio: " + getMunicipio() + "\n";
        dados += "Comuna: " + getComuna() + "\n";
        dados += "Hora de Inicio: " + getHorarioInicio() + "\n";
        dados += "Hora de Termino: " + getHorarioTermino() + "\n";
        dados += "Custo: " + getCusto() + "\n";
        dados += "Estado: " + getEstado() + "\n";
        dados += "Status: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 210 * 2 + 4 * 2 + 1;
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
            nome.write(stream);
            telefone.write(stream);
            genero.write(stream);
            provincia.write(stream);
            municipio.write(stream);
            comuna.write(stream);
            horarioInicio.write(stream);
            horarioFim.write(stream);
            stream.writeFloat(custo);
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
            id = stream.readInt();
            nome.read(stream);
            telefone.read(stream);
            genero.read(stream);
            provincia.read(stream);
            municipio.read(stream);
            comuna.read(stream);
            horarioInicio.read(stream);
            horarioFim.read(stream);
            custo = stream.readFloat();
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
        ClienteFile file = new ClienteFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        ClienteFile file = new ClienteFile();
        file.alterarDados(this);
    }
}