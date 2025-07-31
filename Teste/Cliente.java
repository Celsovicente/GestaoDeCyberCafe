import java.io.Serializable;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String telefone;
    private boolean activo;
    private double custo;

    // Construtor
    public Cliente(int id, String nome, String telefone, boolean activo, double custo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.activo = activo;
        this.custo = custo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean getActivo() {
        return activo;
    }

    public double getCusto() {
        return custo;
    }

    public void setNome(String nome) {
    this.nome = nome;
}

public void setTelefone(String telefone) {
    this.telefone = telefone;
}

public void setAtivo(boolean activo) {
    this.activo = activo;
}

public void setCusto(double custo) {
    this.custo = custo;
}

// Este método "isAtivo" é o mais comum em Java para booleanos
public boolean isAtivo() {
    return activo;
}

}
