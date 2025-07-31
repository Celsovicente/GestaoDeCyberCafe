import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class AlterarSessao extends JPanel {
    private JTextField campoId, campoClienteId, campoEstacaoId, campoCusto;
    private JDateChooser dataInicioChooser, dataFimChooser;

    public AlterarSessao() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Alterar Sessão", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoId = new JTextField(15);
        campoClienteId = new JTextField(15);
        campoEstacaoId = new JTextField(15);
        campoCusto = new JTextField(15);
        dataInicioChooser = new JDateChooser();
        dataFimChooser = new JDateChooser();

        // Ao sair do campo ID, carregar dados da sessão
        campoId.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    int id = Integer.parseInt(campoId.getText());
                    carregarSessaoPorId(id);
                } catch (NumberFormatException ex) {
                    // Campo vazio ou não numérico
                }
            }
        });

        // Linha 1 - ID
        gbc.gridx = 0; gbc.gridy = 0;
        centro.add(new JLabel("ID da Sessão:"), gbc);
        gbc.gridx = 1;
        centro.add(campoId, gbc);

        // Linha 2 - Cliente ID
        gbc.gridx = 0; gbc.gridy = 1;
        centro.add(new JLabel("Novo Cliente ID:"), gbc);
        gbc.gridx = 1;
        centro.add(campoClienteId, gbc);

        // Linha 3 - Estação ID
        gbc.gridx = 0; gbc.gridy = 2;
        centro.add(new JLabel("Nova Estação ID:"), gbc);
        gbc.gridx = 1;
        centro.add(campoEstacaoId, gbc);

        // Linha 4 - Início
        gbc.gridx = 0; gbc.gridy = 3;
        centro.add(new JLabel("Novo Início:"), gbc);
        gbc.gridx = 1;
        centro.add(dataInicioChooser, gbc);

        // Linha 5 - Fim
        gbc.gridx = 0; gbc.gridy = 4;
        centro.add(new JLabel("Novo Fim:"), gbc);
        gbc.gridx = 1;
        centro.add(dataFimChooser, gbc);

        // Linha 6 - Custo
        gbc.gridx = 0; gbc.gridy = 5;
        centro.add(new JLabel("Novo Custo:"), gbc);
        gbc.gridx = 1;
        centro.add(campoCusto, gbc);

        add(centro, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        JButton alterarBtn = new JButton("Alterar");
        JButton limparBtn = new JButton("Limpar");
        botoes.add(alterarBtn);
        botoes.add(limparBtn);
        add(botoes, BorderLayout.SOUTH);

        alterarBtn.addActionListener(e -> alterarSessao());
        limparBtn.addActionListener(e -> limparFormulario());
    }

    private void alterarSessao() {
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
                    if (s.getId() == id) {
                        s.setClienteId(Integer.parseInt(campoClienteId.getText()));
                        s.setEstacaoId(Integer.parseInt(campoEstacaoId.getText()));
                        s.setInicio(dataChooserToLocalDateTime(dataInicioChooser));
                        s.setFim(dataChooserToLocalDateTime(dataFimChooser));
                        s.setCusto(Double.parseDouble(campoCusto.getText()));
                        encontrada = true;
                    }
                    sessoes.add(s);
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
            JOptionPane.showMessageDialog(this, "Sessão alterada com sucesso.");
            limparFormulario(); // limpa após sucesso
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao guardar alterações.");
        }
    }

    private void carregarSessaoPorId(int id) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SessaoFile.dat"))) {
            while (true) {
                try {
                    Sessao s = (Sessao) ois.readObject();
                    if (s.getId() == id) {
                        campoClienteId.setText(String.valueOf(s.getClienteId()));
                        campoEstacaoId.setText(String.valueOf(s.getEstacaoId()));
                        campoCusto.setText(String.valueOf(s.getCusto()));
                        dataInicioChooser.setDate(Date.from(s.getInicio().atZone(ZoneId.systemDefault()).toInstant()));
                        dataFimChooser.setDate(Date.from(s.getFim().atZone(ZoneId.systemDefault()).toInstant()));
                        return;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            JOptionPane.showMessageDialog(this, "Sessão com ID " + id + " não encontrada.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar sessão.");
        }
    }

    private LocalDateTime dataChooserToLocalDateTime(JDateChooser chooser) {
        Date date = chooser.getDate();
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private void limparFormulario() {
        campoId.setText("");
        campoClienteId.setText("");
        campoEstacaoId.setText("");
        campoCusto.setText("");
        dataInicioChooser.setDate(null);
        dataFimChooser.setDate(null);
    }
}
