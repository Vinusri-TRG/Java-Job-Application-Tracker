import java.sql.Connection;
import java.sql.PreparedStatement;

public class ApplicationDAO {

   public static void addApplication(JobApplication application) {

        String sql = "INSERT INTO application " +
                    "(company_name, jobrole, application_date, status, interview_date) " +
                    "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, application.getCompanyname());
            ps.setString(2, application.getJobrole());
            ps.setString(3, application.getApplicationdate());
            ps.setString(4, application.getStatus());
            ps.setString(5, application.getInterviewdate());

            ps.executeUpdate();

            System.out.println("Application added to MySQL successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Failed to add application!");
            e.printStackTrace();
        }
    }


    // VIEW ALL APPLICATIONS

    public static void getAllApplications() {

        String sql = "SELECT * FROM application";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            var rs = ps.executeQuery();

            System.out.println("\n===== ALL JOB APPLICATIONS =====");

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Company: " + rs.getString("company_name"));
                System.out.println("Job Role: " + rs.getString("jobrole"));
                System.out.println("Application Date: " + rs.getString("application_date"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("Interview Date: " + rs.getString("interview_date"));

                System.out.println("-----------------------------");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Failed to retrieve applications!");
            e.printStackTrace();
        }
    }

    public static void updateStatus(int id, String newStatus,String newDate) {

        String sql = "UPDATE application SET status = ?,interview_date = ? WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, newStatus);
            ps.setString(2,newDate);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Application updated successfully!");
            } else {
                System.out.println("Application ID not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Failed to update application!");
            e.printStackTrace();
        }
    }
    
    public static void deleteApplication(int id) {

        String sql = "DELETE FROM application WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Application deleted successfully!");
            } else {
                System.out.println("Application ID not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Failed to delete application!");
            e.printStackTrace();
        }
    }
}