import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class AlterarCliente extends JPanel {
    private JTextField campoId, campoNome, campoTelefone, campoCusto;
    private JCheckBox campoAtivo;
    private Cliente clienteAtual;

    public AlterarCliente() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Alterar Cliente", JLabel.CENTER);
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

        int linha = 0;

        // ID (não editável)
        gbc.gridx = 0; gbc.gridy = linha;
        form.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        campoId = campoTexto();
        campoId.setEditable(true); // Permitimos digitar para buscar
        form.add(campoId, gbc);

        // Nome
        gbc.gridx = 0; gbc.gridy = ++linha;
        form.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        campoNome = campoTexto();
        campoNome.setEditable(false); // Apenas exibir após carregar
        form.add(campoNome, gbc);

        // Telefone
        gbc.gridx = 0; gbc.gridy = ++linha;
        form.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        campoTelefone = campoTexto();
        campoTelefone.setEditable(false);
        form.add(campoTelefone, gbc);

        // Ativo
        gbc.gridx = 0; gbc.gridy = ++linha;
        form.add(new JLabel("Ativo:"), gbc);
        gbc.gridx = 1;
        campoAtivo = new JCheckBox("Sim");
        campoAtivo.setEnabled(false);
        form.add(campoAtivo, gbc);

        // Custo
        gbc.gridx = 0; gbc.gridy = ++linha;
        form.add(new JLabel("Custo:"), gbc);
        gbc.gridx = 1;
        campoCusto = campoTexto();
        campoCusto.setEditable(false);
        form.add(campoCusto, gbc);

        add(form, BorderLayout.CENTER);

        // Botões
        JButton buscarBtn = new JButton("Carregar Cliente");
        JButton salvarBtn = new JButton("Salvar Alterações");

        buscarBtn.addActionListener(e -> carregarClientePorId());
        salvarBtn.addActionListener(e -> salvarAlteracoes());

        JPanel botoes = new JPanel();
        botoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        botoes.add(buscarBtn);
        botoes.add(salvarBtn);
        add(botoes, BorderLayout.SOUTH);
    }

    private JTextField campoTexto() {
        JTextField campo = new JTextField();
        campo.setPreferredSize(new Dimension(200, 30));
        return campo;
    }

    private void carregarClientePorId() {
        int idProcurado;
        try {
            idProcurado = Integer.parseInt(campoId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ClienteFile.dat"))) {
            while (true) {
                try {
                    Cliente cliente = (Cliente) ois.readObject();
                    if (cliente.getId() == idProcurado) {
                        clienteAtual = cliente;
                        preencherCampos(cliente);
                        return;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            JOptionPane.showMessageDialog(this, "Cliente com ID " + idProcurado + " não encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os clientes.");
        }
    }

    private void preencherCampos(Cliente cliente) {
        campoNome.setText(cliente.getNome());
        campoTelefone.setText(cliente.getTelefone());
        campoAtivo.setSelected(cliente.isAtivo());
        campoCusto.setText(String.valueOf(cliente.getCusto()));

        campoNome.setEditable(true);
        campoTelefone.setEditable(true);
        campoCusto.setEditable(true);
        campoAtivo.setEnabled(true);
    }

    private void salvarAlteracoes() {
        if (clienteAtual == null) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente carregado.");
            return;
        }

        clienteAtual.setNome(campoNome.getText());
        clienteAtual.setTelefone(campoTelefone.getText());
        clienteAtual.setAtivo(campoAtivo.isSelected());

        try {
            double custo = Double.parseDouble(campoCusto.getText());
            clienteAtual.setCusto(custo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Custo inválido.");
            return;
        }

        // Atualizar no ficheiro
        List<Cliente> clientes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ClienteFile.dat"))) {
            while (true) {
                try {
                    Cliente c = (Cliente) ois.readObject();
                    if (c.getId() == clienteAtual.getId()) {
                        clientes.add(clienteAtual);
                    } else {
                        clientes.add(c);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler os clientes.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ClienteFile.dat"))) {
            for (Cliente c : clientes) {
                oos.writeObject(c);
            }
            JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
            limparCampos(); // limpa os campos depois de salvar
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar alterações.");
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoTelefone.setText("");
        campoAtivo.setSelected(false);
        campoCusto.setText("");

        campoNome.setEditable(false);
        campoTelefone.setEditable(false);
        campoCusto.setEditable(false);
        campoAtivo.setEnabled(false);

        clienteAtual = null;
    }
}
