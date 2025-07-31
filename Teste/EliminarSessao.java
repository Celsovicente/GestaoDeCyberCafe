import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class EliminarSessao extends JPanel {
    private JTextField campoId;

    public EliminarSessao() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Sessão", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        centro.add(new JLabel("ID da Sessão:"), gbc);
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

        confirmarBtn.addActionListener(e -> eliminarSessao());
        cancelarBtn.addActionListener(e -> campoId.setText(""));
    }

    private void eliminarSessao() {
        int id;
        try {
            id = Integer.parseInt(campoId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
            return;
        }

        List<Sessao> sessoes = new ArrayList<>();
        boolean encontrada = false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SessaoFile.dat"))) {
            while (true) {
                try {
                    Sessao s = (Sessao) ois.readObject();
                    if (s.getId() != id) {
                        sessoes.add(s);
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
            JOptionPane.showMessageDialog(this, "Sessão com ID " + id + " não encontrada.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SessaoFile.dat"))) {
            for (Sessao s : sessoes) {
                oos.writeObject(s);
            }
            JOptionPane.showMessageDialog(this, "Sessão eliminada com sucesso.");
            campoId.setText("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao guardar alterações.");
        }
    }
}
