import java.sql.*;
import java.util.Scanner;

public class EmployeeManagerApp {
    static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "root";
    static final String PASS = "your_password_here"; 

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n--- Employee Manager ---");
                System.out.println("1. Add Employee\n2. View All\n3. Update Employee\n4. Delete Employee\n5. Exit");
                System.out.print("Select: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addEmployee(conn, sc);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateEmployee(conn, sc);
                    case 4 -> deleteEmployee(conn, sc);
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        String sql = "INSERT INTO employees (name, position) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.print("Name: ");
        sc.nextLine();
        ps.setString(1, sc.nextLine());
        System.out.print("Position: ");
        ps.setString(2, sc.nextLine());
        ps.executeUpdate();
        System.out.println("✅ Employee added.");
    }

    static void viewEmployees(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM employees");
        while (rs.next()) {
            System.out.printf("%d | %s | %s\n", rs.getInt("id"), rs.getString("name"), rs.getString("position"));
        }
    }

    static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        String sql = "UPDATE employees SET name=?, position=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("New Name: ");
        ps.setString(1, sc.nextLine());
        System.out.print("New Position: ");
        ps.setString(2, sc.nextLine());
        ps.setInt(3, id);
        ps.executeUpdate();
        System.out.println("✅ Updated.");
    }

    static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        System.out.print("Enter ID to delete: ");
        ps.setInt(1, sc.nextInt());
        ps.executeUpdate();
        System.out.println("✅ Deleted.");
    }
}
