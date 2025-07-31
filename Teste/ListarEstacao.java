import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class ListarEstacao extends JPanel {
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public ListarEstacao() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Lista de Estações", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        String[] colunas = {"ID da Estação", "ID do Cliente", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        tabela.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        carregarEstacoes();
    }

    private void carregarEstacoes() {
        File arquivo = new File("EstacaoFile.dat");

        if (!arquivo.exists() || arquivo.length() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhuma estação cadastrada ainda.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            while (true) {
                try {
                    Estacao estacao = (Estacao) ois.readObject();

                    String statusTexto = estacao.isStatus() ? "Ocupada" : "Livre";

                    modeloTabela.addRow(new Object[]{
                        estacao.getId(),
                        estacao.getClienteId(),
                        statusTexto
                    });

                } catch (EOFException e) {
                    break; // fim do ficheiro
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar as estações.");
        }
    }
}
