import java.io.Serializable;
import java.time.LocalDateTime;

public class Sessao implements Serializable {
    private int id;
    private int clienteId;
    private int estacaoId;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private double custo;

    public Sessao(int id, int clienteId, int estacaoId, LocalDateTime inicio, LocalDateTime fim, double custo) {
        this.id = id;
        this.clienteId = clienteId;
        this.estacaoId = estacaoId;
        this.inicio = inicio;
        this.fim = fim;
        this.custo = custo;
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getEstacaoId() {
        return estacaoId;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public double getCusto() {
        return custo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setEstacaoId(int estacaoId) {
        this.estacaoId = estacaoId;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
}
