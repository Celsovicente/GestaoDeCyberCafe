import javax.swing.*;
import java.awt.*;

public class ApresentacaoVisao extends JFrame {

    public ApresentacaoVisao() {
        setTitle("Apresentação do Sistema");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------- Cabeçalho ----------
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("GESTÃO DE UM CYBER CSFÉ");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Universidade Católica de Angola - Faculdade de Engenharia");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(lblTitulo);
        header.add(Box.createVerticalStrut(5));
        header.add(lblSubtitulo);

        // ---------- Corpo ----------
        JTextArea texto = new JTextArea();
        texto.setEditable(false);
        texto.setFont(new Font("Monospaced", Font.PLAIN, 14));
        texto.setText(
            "\n----------------------------------------------------------\n" +
            "PROJECTO DE FUNDAMENTOS DE PROGRAMAÇÃO I ANO 24-25\n" +
            "AUTORA: TINILSA DOMINGOS, N. 34298\n" +
            "----------------------------------------------------------\n" +
            "TEMA: GESTÃO DE PROJETOS DE TECNOLOGIA DA INFORMAÇÃO\n" +
            "DOCENTE: ENG. OSVALDO RAMOS\n" +
            "----------------------------------------------------------\n" +
            "OBS: USO EXCLUSIVO DENTRO DA UCAN\n" +
            "----------------------------------------------------------\n" +
            "SE CONCORDA COM OS TERMOS DE USO CLIQUE EM 'SIM'\n"
        );
        texto.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(texto);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // ---------- Rodapé com Botões ----------
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnSim = new JButton("Sim");
        JButton btnNao = new JButton("Não");

        btnSim.setFocusPainted(false);
        btnNao.setFocusPainted(false);

        btnSim.setPreferredSize(new Dimension(100, 35));
        btnNao.setPreferredSize(new Dimension(100, 35));

        botoes.add(btnSim);
        botoes.add(btnNao);

        // ---------- Eventos ----------
        btnSim.addActionListener(e -> {
            dispose();
            new LoginVisao(); // Chama a tela de login
        });

        btnNao.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Até a próxima!", "Saída", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        // ---------- Montagem final ----------
        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        setVisible(true);
    }
}
