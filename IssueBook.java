import java.sql.Connection;
import java.sql.PreparedStatement;

public class IssueBook {

    public static void issue(int bookId, int memberId) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO issues(book_id, member_id, issue_date, status) VALUES (?, ?, CURDATE(), 'ISSUED')";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookId);
            ps.setInt(2, memberId);

            ps.executeUpdate();

            System.out.println("Book Issued!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}