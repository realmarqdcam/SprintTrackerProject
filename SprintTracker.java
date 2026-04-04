import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SprintTracker {
    private String date;
    private String event;
    private double time_Seconds;
    private String note;

    public static void main (String [] args) {
        String url = "jdbc:sqlite:C:/Users/marqd/NewProjectJST/test.db";
        System.out.println(new java.io.File("test.db").getAbsolutePath());
        String sql = "INSERT INTO test (Date, Event, Time_Seconds, Note) VALUES (?, ?, ?, ?)";

        Scanner plug = new Scanner(System.in);

            try (Connection conn = DriverManager.getConnection(url); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                Status(conn);
                Insert(pstmt, conn, plug);

            }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void Status(Connection pop) {
        if (pop != null) {
            System.out.println("Connection to database successful!");
        }
    }

    public static void Insert(PreparedStatement lol, Connection pop, Scanner plug) {
            try {

                while (true) {
                    System.out.print("Do you want to record a new performance(Y/N)?  ");
                    String QA = plug.nextLine();
                        if (QA.equalsIgnoreCase("Y")) {
                             System.out.print("Enter the date of the event (MM/DD/YYYY): ");
                                String date = plug.nextLine();
                            System.out.print("Enter the event (e.g. 100M, 200M, etc.): ");
                                String event = plug.nextLine();
                            System.out.print("Enter the time in seconds: ");
                                double time_Seconds = plug.nextDouble();
                                    plug.nextLine();
                            System.out.print("Enter any notes about the performance: ");
                                String note = plug.nextLine();

                            lol.setString(1, date);
                            lol.setString(2, event);
                            lol.setDouble(3, time_Seconds);
                            lol.setString(4, note);

                            int x = lol.executeUpdate();
                                if (x > 0) {
                                    System.out.println("Recorded successfully.");
                            } else {
                                System.out.println("Insert failed.");
                            }
                        } else if (QA.equalsIgnoreCase("N")) {
                            System.out.println("Exiting the program.");
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                        }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public String getDate() {
        return date;
    }
    public String getEvent() {
        return event;
    }
    public double getTime_Seconds() {
        return time_Seconds;
    }
    public String getNote() {
        return note;
    }
    public void setDate (String date) {
        this.date = date;
    }
    public void setEvent (String event) {
        this.event = event;
    }
    public double setTime_Seconds (double time_Seconds) {
        this.time_Seconds = time_Seconds;
        return time_Seconds;
    }
    public void setNote (String note) {
        this.note = note;
    }
}