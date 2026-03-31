import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LibraryGUI extends JFrame {

    JTextField txtTitle, txtAuthor, txtQty, txtName, txtPhone;
    JTable table;
    DefaultTableModel model;

    public LibraryGUI() {
        setTitle("Library System");
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(72, 61, 139),
                        getWidth(), getHeight(), new Color(0, 191, 255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        main.setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("📚 Library Dashboard", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        main.add(header, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        model = new DefaultTableModel(new String[]{"ID", "Title/Name", "Author/Phone", "Qty"}, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setBackground(new Color(255, 255, 255, 180));

        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);

        tablePanel.add(scroll);
        main.add(tablePanel, BorderLayout.CENTER);

        JPanel form = new JPanel(new GridLayout(5, 4, 12, 12));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        form.setBackground(new Color(255, 255, 255, 120));

        txtTitle = createField();
        txtAuthor = createField();
        txtQty = createField();
        txtName = createField();
        txtPhone = createField();

        form.add(createLabel("📖 Title")); form.add(txtTitle);
        form.add(createLabel("✍ Author")); form.add(txtAuthor);

        form.add(createLabel("🔢 Qty")); form.add(txtQty);
        JButton btnAddBook = createButton("➕ Add Book");
        JButton btnViewBooks = createButton("📚 View Books");
        form.add(btnAddBook); form.add(btnViewBooks);

        form.add(createLabel("👤 Name")); form.add(txtName);
        form.add(createLabel("📞 Phone")); form.add(txtPhone);

        JButton btnAddMember = createButton("👥 Add Member");
        JButton btnViewMembers = createButton("📋 View Members");
        form.add(btnAddMember); form.add(btnViewMembers);

        JButton btnDeleteMember = createButton("🗑 Delete Member");
        JButton btnIssue = createButton("📖 Issue Book");
        JButton btnReturn = createButton("🔄 Return Book");
        JButton btnUpdate = createButton("✏️ Update Book");
        JButton btnDeleteBook = createButton("🗑 Delete Book");

        form.add(btnDeleteMember);
        form.add(btnIssue);
        form.add(btnReturn);
        form.add(btnUpdate);
        form.add(btnDeleteBook);

        main.add(form, BorderLayout.SOUTH);
        add(main);

        btnAddBook.addActionListener(e -> addBook());
        btnViewBooks.addActionListener(e -> loadBooks());
        btnAddMember.addActionListener(e -> addMember());
        btnViewMembers.addActionListener(e -> loadMembers());
        btnDeleteMember.addActionListener(e -> deleteMember());
        btnIssue.addActionListener(e -> issueBook());
        btnReturn.addActionListener(e -> returnBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDeleteBook.addActionListener(e -> deleteBook());
    }

    private JTextField createField() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return f;
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return l;
    }

    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(new Color(30, 144, 255));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));

        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(new Color(65, 180, 255));
                b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
                b.setBackground(new Color(30, 144, 255));
            }
        });

        return b;
    }

    private void addBook() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO books(title, author, quantity) VALUES (?, ?, ?)");
            ps.setString(1, txtTitle.getText());
            ps.setString(2, txtAuthor.getText());
            ps.setInt(3, Integer.parseInt(txtQty.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book Added!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void addMember() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO members(name, phone) VALUES (?, ?)");
            ps.setString(1, txtName.getText());
            ps.setString(2, txtPhone.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Member Added!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void deleteMember() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID to Delete"));
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM members WHERE member_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Member Deleted!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadBooks() {
        try {
            model.setRowCount(0);
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM books");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadMembers() {
        try {
            model.setRowCount(0);
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM members");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), "-"});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void issueBook() {
        try {
            int bookId = Integer.parseInt(JOptionPane.showInputDialog("Enter Book ID"));
            int memberId = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID"));

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO issues(book_id, member_id, issue_date, status) VALUES (?, ?, CURDATE(), 'ISSUED')");
            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Book Issued!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void returnBook() {
        try {
            int issueId = Integer.parseInt(JOptionPane.showInputDialog("Enter Issue ID"));
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE issues SET return_date=CURDATE(), status='RETURNED' WHERE issue_id=?");
            ps.setInt(1, issueId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Book Returned!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void updateBook() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Book ID"));
            String title = JOptionPane.showInputDialog("New Title");
            String author = JOptionPane.showInputDialog("New Author");
            int qty = Integer.parseInt(JOptionPane.showInputDialog("New Quantity"));

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE books SET title=?, author=?, quantity=? WHERE book_id=?");
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, qty);
            ps.setInt(4, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Book Updated!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void deleteBook() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Book ID"));
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE book_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Book Deleted!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI().setVisible(true));
    }
}