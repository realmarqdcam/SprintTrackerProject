import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SprintTracker{
    public static void main (String [] args) {
        String url = "jdbc:sqlite:C:/Users/marqd/NewProjectJST/test.db";

        System.out.println(new java.io.File("test.db").getAbsolutePath());

        try (Connection conn = DriverManager.getConnection(url)) {

            
            String sql = "INSERT INTO test (ID, Date, Event, Time_Seconds, Note) VALUES (0, '1/16/2026', 400, '49.58', 'N/A')";
            Statement stmt = conn.createStatement();

            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }
            
            int x = stmt.executeUpdate(sql);
                        if ( x > 0) {
                            System.out.println("Recorded successfully.");
                        }
                        else {
                        System.out.println("Insert failed.");
                            conn.close();
                        }
                        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}