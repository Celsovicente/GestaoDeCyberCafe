import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.time.format.DateTimeFormatter;

public class ListarSessao extends JPanel {
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public ListarSessao() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Lista de Sessões", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        String[] colunas = {"ID", "Estação ID", "Cliente ID", "Data de início", "Data de fim"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);
        tabela.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        carregarSessoes();
    }

    private void carregarSessoes() {
        File arquivo = new File("SessaoFile.dat");

        if (!arquivo.exists() || arquivo.length() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhuma sessão cadastrada ainda.");
            return;
        }

        // Formatar a data para dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            while (true) {
                try {
                    Sessao sessao = (Sessao) ois.readObject();
                    modeloTabela.addRow(new Object[]{
                        sessao.getId(),
                        sessao.getEstacaoId(),
                        sessao.getClienteId(),
                        sessao.getInicio().format(formatter),
                        sessao.getFim().format(formatter)
                    });
                } catch (EOFException e) {
                    break; // Fim do arquivo
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar as sessões.");
        }
    }
}
