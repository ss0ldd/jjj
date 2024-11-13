import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "PROSTOPIZDEC228";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM driver WHERE age IS NOT NULL AND CAST(age AS INTEGER) > 25");
        System.out.println("Водители старше 25 лет:");
        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("name"));
        }
        
        String sqlInsertUser = "INSERT INTO driver(name, surename, age) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        for (int i = 0; i < 6; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя: ");
            String firstName = scanner.nextLine();
            System.out.println("Введите фамилию: ");
            String secondName = scanner.nextLine();
            System.out.println("Введите возраст: ");
            String age = scanner.nextLine();
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            preparedStatement.setString(3, age);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
    }
}
