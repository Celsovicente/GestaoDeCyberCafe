import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ListarClientes extends JPanel {
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public ListarClientes() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Lista de Clientes", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // Cabeçalhos da tabela
        String[] colunas = {"ID", "Nome", "Telefone", "Activo", "Custo (Kz)"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        tabela.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        carregarClientes();
    }

    private void carregarClientes() {
        File arquivo = new File("ClienteFile.dat");

        if (!arquivo.exists() || arquivo.length() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente cadastrado ainda.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            while (true) {
                try {
                    Cliente cliente = (Cliente) ois.readObject();
                    modeloTabela.addRow(new Object[]{
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getTelefone(),
                        cliente.getActivo(),
                        cliente.getCusto()
                    });
                } catch (EOFException e) {
                    break; // Fim do ficheiro
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar os clientes.");
        }
    }
}
