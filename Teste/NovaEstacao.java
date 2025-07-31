import javax.swing.*;
import java.awt.*;
import java.io.*;

public class NovaEstacao extends JPanel {
    private JTextField campoId, campoClientId;
    private JComboBox<String> comboStatus;

    public NovaEstacao() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Nova Estação", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // ID da Estação
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("ID da Estação:"), gbc);
        gbc.gridx = 1;
        campoId = new JTextField();
        form.add(campoId, gbc);

        // ID do Cliente
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("ID do Cliente:"), gbc);
        gbc.gridx = 1;
        campoClientId = new JTextField();
        form.add(campoClientId, gbc);

        // Status (ComboBox)
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        comboStatus = new JComboBox<>(new String[]{"Livre", "Ocupada"});
        form.add(comboStatus, gbc);

        add(form, BorderLayout.CENTER);

        JButton btnSalvar = new JButton("Salvar Estação");
        btnSalvar.addActionListener(e -> salvarEstacao());
        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        add(botoes, BorderLayout.SOUTH);
    }

    private void salvarEstacao() {
        try {
            int id = Integer.parseInt(campoId.getText());
            int clientId = Integer.parseInt(campoClientId.getText());

            // Converte a escolha da ComboBox para booleano
            String statusSelecionado = (String) comboStatus.getSelectedItem();
            boolean status = statusSelecionado.equals("Ocupada");

            Estacao estacao = new Estacao(id, clientId, status);

            // Verifica se o ficheiro já existe para evitar sobrescrever o cabeçalho do stream
            File arquivo = new File("EstacaoFile.dat");
            boolean append = arquivo.exists();

            try (ObjectOutputStream oos = append
                    ? new AppendableObjectOutputStream(new FileOutputStream(arquivo, true))
                    : new ObjectOutputStream(new FileOutputStream(arquivo))) {

                oos.writeObject(estacao);
                JOptionPane.showMessageDialog(this, "Estação salva com sucesso!");
                limparCampos();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar. Verifique os dados inseridos.");
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoClientId.setText("");
        comboStatus.setSelectedIndex(0);
    }

    // Classe interna para evitar erro de cabeçalho ao fazer append em ObjectOutputStream
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset(); // não escreve um novo cabeçalho
        }
    }
}
