import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class EliminarCliente extends JPanel {
    private JTextField campoId;

    public EliminarCliente() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Cliente", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel mensagem = new JLabel("Digite o ID do cliente que deseja eliminar:");
        mensagem.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        centro.add(mensagem, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1;
        centro.add(new JLabel("ID do Cliente:"), gbc);
        gbc.gridx = 1;
        campoId = new JTextField(15);
        centro.add(campoId, gbc);

        add(centro, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        botoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        JButton confirmarBtn = new JButton("Confirmar");
        JButton cancelarBtn = new JButton("Cancelar");
        botoes.add(confirmarBtn);
        botoes.add(cancelarBtn);

        add(botoes, BorderLayout.SOUTH);

        confirmarBtn.addActionListener(e -> eliminarCliente());
        cancelarBtn.addActionListener(e -> limparCampo());
    }

    private void eliminarCliente() {
        int id;
        try {
            id = Integer.parseInt(campoId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
            return;
        }

        List<Cliente> clientes = new ArrayList<>();
        boolean clienteEncontrado = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ClienteFile.dat"))) {
            while (true) {
                try {
                    Cliente cliente = (Cliente) ois.readObject();
                    if (cliente.getId() != id) {
                        clientes.add(cliente);
                    } else {
                        clienteEncontrado = true;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler os dados.");
            return;
        }

        if (!clienteEncontrado) {
            JOptionPane.showMessageDialog(this, "Cliente com ID " + id + " não encontrado.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ClienteFile.dat"))) {
            for (Cliente c : clientes) {
                oos.writeObject(c);
            }
            JOptionPane.showMessageDialog(this, "Cliente eliminado com sucesso.");
            limparCampo();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar alterações.");
        }
    }

    private void limparCampo() {
        campoId.setText("");
    }
}
