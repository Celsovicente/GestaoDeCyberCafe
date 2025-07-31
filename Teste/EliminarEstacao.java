import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class EliminarEstacao extends JPanel {
    private JTextField campoId;

    public EliminarEstacao() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Estação", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        centro.add(new JLabel("ID da Estação:"), gbc);
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

        confirmarBtn.addActionListener(e -> eliminarEstacao());
        cancelarBtn.addActionListener(e -> campoId.setText(""));
    }

    private void eliminarEstacao() {
        int id;
        try {
            id = Integer.parseInt(campoId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
            return;
        }

        List<Estacao> estacoes = new ArrayList<>();
        boolean encontrada = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EstacaoFile.dat"))) {
            while (true) {
                try {
                    Estacao e = (Estacao) ois.readObject();
                    if (e.getId() != id) {
                        estacoes.add(e);
                    } else {
                        encontrada = true;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
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
            JOptionPane.showMessageDialog(this, "Estação eliminada com sucesso.");
            campoId.setText("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao guardar alterações.");
        }
    }
}
