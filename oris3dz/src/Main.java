import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "PROSTOPIZDEC228";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from users");

        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("firstName") + " " + result.getString("secondName"));
        }

        String sqlInsertUser = "insert into users(firstName, secondName, age) " +
                "values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        System.out.println("Сколько пользователей вы хотите ввести?");
        Scanner scanner = new Scanner(System.in);
        int users = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < users; i++) {
            System.out.println("Введите фамилию: ");
            String firstName = scanner.nextLine();
            System.out.println("Введите имя: ");
            String secondName = scanner.nextLine();
            System.out.println("Введите возраст: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            preparedStatement.setInt(3, age);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
    }
}