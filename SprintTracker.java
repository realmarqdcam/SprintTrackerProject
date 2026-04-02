import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SprintTracker{
    public static void main (String [] args) {
        System.out.println("Sprint Tracker Project loading...");
        String url = "jdbc:sqlite:C:/Users/marqd/NewProjectJST/Sessions.db";

        System.out.println(new java.io.File("Sessions.db").getAbsolutePath());

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        {
            String sql = "INSERT INTO Sessions (ID, Date, Event, Time, Note) VALUES (0, '1/16/2026', 400, '49.58', 'N/A')";
                conn.createStatement().execute(sql);
                    int x = stmt.executeUpdate(sql);
                        if ( x > 0) {
                            System.out.println("Recorded successfully.");
                        }
                        else {
                        System.out.println("Insert failed.");
                            conn.close();
                        }
        }
    }
}