import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddBook {

    public static void addBook(String title, String author, int qty) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO books(title, author, quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, qty);

            ps.executeUpdate();

            System.out.println("Book Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}