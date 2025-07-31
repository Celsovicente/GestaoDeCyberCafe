import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class NovaSessao extends JPanel {
    private JTextField campoId, campoClienteId, campoEstacaoId, campoCusto;
    private JDateChooser campoInicio, campoFim;

    public NovaSessao() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Nova Sessão", JLabel.CENTER);
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

        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("ID da Sessão:"), gbc);
        gbc.gridx = 1;
        campoId = new JTextField();
        form.add(campoId, gbc);

        // Cliente ID
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("ID do Cliente:"), gbc);
        gbc.gridx = 1;
        campoClienteId = new JTextField();
        form.add(campoClienteId, gbc);

        // Estacao ID
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("ID da Estação:"), gbc);
        gbc.gridx = 1;
        campoEstacaoId = new JTextField();
        form.add(campoEstacaoId, gbc);

        // Início (com JDateChooser)
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Data de Início:"), gbc);
        gbc.gridx = 1;
        campoInicio = new JDateChooser();
        campoInicio.setDateFormatString("dd/MM/yyyy HH:mm");
        form.add(campoInicio, gbc);

        // Fim (com JDateChooser)
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Data de Fim:"), gbc);
        gbc.gridx = 1;
        campoFim = new JDateChooser();
        campoFim.setDateFormatString("dd/MM/yyyy HH:mm");
        form.add(campoFim, gbc);

        // Custo
        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Custo (Kz):"), gbc);
        gbc.gridx = 1;
        campoCusto = new JTextField();
        form.add(campoCusto, gbc);

        add(form, BorderLayout.CENTER);

        JButton btnSalvar = new JButton("Salvar Sessão");
        btnSalvar.addActionListener(e -> salvarSessao());
        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        add(botoes, BorderLayout.SOUTH);
    }

    private void salvarSessao() {
        try {
            int id = Integer.parseInt(campoId.getText());
            int clienteId = Integer.parseInt(campoClienteId.getText());
            int estacaoId = Integer.parseInt(campoEstacaoId.getText());

            Date dataInicio = campoInicio.getDate();
            Date dataFim = campoFim.getDate();

            if (dataInicio == null || dataFim == null) {
                throw new IllegalArgumentException("As datas não podem estar vazias.");
            }

            LocalDateTime inicio = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime fim = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            double custo = Double.parseDouble(campoCusto.getText());

            Sessao sessao = new Sessao(id, clienteId, estacaoId, inicio, fim, custo);

            File arquivo = new File("SessaoFile.dat");
            boolean append = arquivo.exists();

            try (ObjectOutputStream oos = append
                    ? new AppendableObjectOutputStream(new FileOutputStream(arquivo, true))
                    : new ObjectOutputStream(new FileOutputStream(arquivo))) {

                oos.writeObject(sessao);
                JOptionPane.showMessageDialog(this, "Sessão salva com sucesso!");
                limparCampos();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar. Verifique os dados inseridos.");
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoClienteId.setText("");
        campoEstacaoId.setText("");
        campoInicio.setDate(null);
        campoFim.setDate(null);
        campoCusto.setText("");
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}
