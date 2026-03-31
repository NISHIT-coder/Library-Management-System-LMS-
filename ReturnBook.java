import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReturnBook {

    public static void returnBook(int issueId) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE issues SET return_date = CURDATE(), status = 'RETURNED' WHERE issue_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, issueId);

            ps.executeUpdate();

            System.out.println("Book Returned!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}