import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class AlterarEstacao extends JPanel {
    private JTextField campoId, campoClientId;
    private JComboBox<String> comboStatus;

    public AlterarEstacao() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Alterar Estação", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoId = new JTextField(15);
        campoClientId = new JTextField(15);
        comboStatus = new JComboBox<>(new String[]{"Livre", "Ocupada"});

        // Preenche os dados ao perder foco no ID
        campoId.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int id = Integer.parseInt(campoId.getText());
                    carregarEstacaoPorId(id);
                } catch (NumberFormatException ex) {
                    // Ignora
                }
            }
        });

        // Linha 1 - ID
        gbc.gridx = 0; gbc.gridy = 0;
        centro.add(new JLabel("ID da Estação:"), gbc);
        gbc.gridx = 1;
        centro.add(campoId, gbc);

        // Linha 2 - Cliente ID
        gbc.gridx = 0; gbc.gridy = 1;
        centro.add(new JLabel("ID do Cliente:"), gbc);
        gbc.gridx = 1;
        centro.add(campoClientId, gbc);

        // Linha 3 - Status
        gbc.gridx = 0; gbc.gridy = 2;
        centro.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        centro.add(comboStatus, gbc);

        add(centro, BorderLayout.CENTER);

        // Botões
        JPanel botoes = new JPanel();
        JButton alterarBtn = new JButton("Alterar");
        JButton limparBtn = new JButton("Limpar");
        botoes.add(alterarBtn);
        botoes.add(limparBtn);
        add(botoes, BorderLayout.SOUTH);

        alterarBtn.addActionListener(e -> alterarEstacao());
        limparBtn.addActionListener(e -> limparFormulario());
    }

    private void alterarEstacao() {
        int id;
        int clienteId;
        boolean status;

        try {
            id = Integer.parseInt(campoId.getText());
            clienteId = Integer.parseInt(campoClientId.getText());
            String statusTexto = (String) comboStatus.getSelectedItem();
            status = statusTexto.equals("Ocupada");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifique se os campos numéricos estão corretos.");
            return;
        }

        List<Estacao> estacoes = new ArrayList<>();
        boolean encontrada = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EstacaoFile.dat"))) {
            while (true) {
                try {
                    Estacao e = (Estacao) ois.readObject();
                    if (e.getId() == id) {
                        e.setClienteId(clienteId);
                        e.setStatus(status);
                        encontrada = true;
                    }
                    estacoes.add(e);
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o ficheiro.");
            return;
        }

        if (!encontrada) {
            JOptionPane.showMessageDialog(this, "Estação com ID " + id + " não encontrada.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EstacaoFile.dat"))) {
            for (Estacao e : estacoes) {
                oos.writeObject(e);
            }
            JOptionPane.showMessageDialog(this, "Estação alterada com sucesso.");
            limparFormulario();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao guardar alterações.");
        }
    }

    private void carregarEstacaoPorId(int id) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EstacaoFile.dat"))) {
            while (true) {
                try {
                    Estacao e = (Estacao) ois.readObject();
                    if (e.getId() == id) {
                        campoClientId.setText(String.valueOf(e.getClienteId()));
                        comboStatus.setSelectedItem(e.isStatus() ? "Ocupada" : "Livre");
                        return;
                    }
                } catch (EOFException ex) {
                    break;
                }
            }
            JOptionPane.showMessageDialog(this, "Estação com ID " + id + " não encontrada.");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar estação.");
        }
    }

    private void limparFormulario() {
        campoId.setText("");
        campoClientId.setText("");
        comboStatus.setSelectedIndex(0);
    }
}
