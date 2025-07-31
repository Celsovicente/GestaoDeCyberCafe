import java.io.Serializable;

public class Estacao implements Serializable {
    private int id;
    private int clienteId;
    private boolean status; // true = ocupada, false = livre

    public Estacao(int id, int clienteId, boolean status) {
        this.id = id;
        this.clienteId = clienteId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
