import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddMember {

    public static void addMember(String name, String phone) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO members(name, phone) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, phone);

            ps.executeUpdate();

            System.out.println("Member Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
