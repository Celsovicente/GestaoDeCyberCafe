import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NovoCliente extends JPanel {
    private JTextField campoId, campoNome, campoTelefone, campoActivo, campoCusto;

    public NovoCliente() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Novo Cliente", JLabel.CENTER);
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

        // Campos do formulário
        campoId = adicionarCampo("ID:", form, gbc, 0);
        campoNome = adicionarCampo("Nome do Cliente:", form, gbc, 1);
        campoTelefone = adicionarCampo("Telefone:", form, gbc, 2);
        campoActivo = adicionarCampo("Activo (true/false):", form, gbc, 3);
        campoCusto = adicionarCampo("Custo (Kz):", form, gbc, 4);

        add(form, BorderLayout.CENTER);

        JButton btnSalvar = new JButton("Salvar Cliente");
        btnSalvar.addActionListener(e -> salvarCliente());

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        add(botoes, BorderLayout.SOUTH);
    }

    private JTextField adicionarCampo(String label, JPanel panel, GridBagConstraints gbc, int y) {
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        JTextField campo = criarCampoTexto();
        panel.add(campo, gbc);
        return campo;
    }

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField();
        campo.setPreferredSize(new Dimension(200, 30));
        return campo;
    }

    private void salvarCliente() {
        if (campoId.getText().isEmpty() || campoNome.getText().isEmpty() || campoTelefone.getText().isEmpty()
                || campoActivo.getText().isEmpty() || campoCusto.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(campoId.getText().trim());
            String nome = campoNome.getText().trim();
            String telefone = campoTelefone.getText().trim();
            boolean activo = Boolean.parseBoolean(campoActivo.getText().trim().toLowerCase());
            double custo = Double.parseDouble(campoCusto.getText().trim());

            Cliente cliente = new Cliente(id, nome, telefone, activo, custo);

            File arquivo = new File("ClienteFile.dat");
            boolean existe = arquivo.exists() && arquivo.length() > 0;

            try (ObjectOutputStream oos = existe
                    ? new AppendableObjectOutputStream(new FileOutputStream(arquivo, true))
                    : new ObjectOutputStream(new FileOutputStream(arquivo))) {
                oos.writeObject(cliente);
                JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
                limparCampos();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro nos dados. Verifique o ID, Activo (true/false) e Custo (Kz).");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar cliente.");
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoTelefone.setText("");
        campoActivo.setText("");
        campoCusto.setText("");
    }

    // Classe auxiliar para permitir múltiplos objetos no mesmo ficheiro
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset(); // não escreve o cabeçalho de novo
        }
    }
}
