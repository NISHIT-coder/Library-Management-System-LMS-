import java.sql.*;

public class ViewMembers {

    public static void viewMembers() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM members";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n---- MEMBER LIST ----");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("member_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("phone")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}