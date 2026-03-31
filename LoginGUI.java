import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginGUI extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;

    public LoginGUI() {
        setTitle("Library Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(72, 61, 139),
                        getWidth(), getHeight(), new Color(0, 191, 255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());

        JPanel loginBox = new JPanel(new GridLayout(3, 2, 10, 10)) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        loginBox.setOpaque(false);
        loginBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtUser = new JTextField();
        txtPass = new JPasswordField();

        loginBox.add(new JLabel("Username:"));
        loginBox.add(txtUser);
        loginBox.add(new JLabel("Password:"));
        loginBox.add(txtPass);

        JButton btnLogin = new JButton("Login");
        styleButton(btnLogin);
        loginBox.add(new JLabel());
        loginBox.add(btnLogin);

        panel.add(loginBox);
        add(panel);

        btnLogin.addActionListener(e -> login());
    }

    private void styleButton(JButton b) {
        b.setBackground(new Color(30, 144, 255));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));

        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(new Color(65, 180, 255));
            }
            public void mouseExited(MouseEvent e) {
                b.setBackground(new Color(30, 144, 255));
            }
        });
    }

    private void login() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        // Simple authentication (you can connect DB here)
        if (user.equals("admin") && pass.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new LibraryGUI().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
