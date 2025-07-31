import javax.swing.*;
import java.awt.*;

public class LoginVisao extends JFrame {
    public LoginVisao() {
        setTitle("Login - Sistema de Cyber Café");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Acesso ao Sistema");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelLogin = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsuario = new JLabel("Usuário:");
        JTextField txtUsuario = new JTextField(15);
        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(15);
        JButton btnEntrar = new JButton("Entrar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelLogin.add(lblUsuario, gbc);
        gbc.gridx = 1;
        painelLogin.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelLogin.add(lblSenha, gbc);
        gbc.gridx = 1;
        painelLogin.add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelLogin.add(btnEntrar, gbc);

        painelPrincipal.add(painelLogin, BorderLayout.CENTER);
        add(painelPrincipal);

        // Removido: btnEntrar.setBackground(...) e setForeground(...)

        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            if (usuario.equals("admin") && senha.equals("12345")) {
                dispose();
                new MenuPrincipal();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
