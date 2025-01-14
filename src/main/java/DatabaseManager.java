import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:hangman.db";

    static {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(DB_URL)) {
                String createTableQuery = "CREATE TABLE IF NOT EXISTS game_records (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "player_name TEXT, " +
                        "game_type TEXT, " +
                        "remaining_attempts INTEGER, " +
                        "hints_used INTEGER, " +
                        "difficulty_level TEXT" +
                        ");";
                try (Statement statement = connection.createStatement()) {
                    statement.execute(createTableQuery);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveGame(String playerName, String gameType, int remainingAttempts, int hintsUsed, String difficultyLevel) {
        String insertQuery = "INSERT INTO game_records (player_name, game_type, remaining_attempts, hints_used, difficulty_level) VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, playerName);
            preparedStatement.setString(2, gameType);
            preparedStatement.setInt(3, remainingAttempts);
            preparedStatement.setInt(4, hintsUsed);
            preparedStatement.setString(5, difficultyLevel);
            preparedStatement.executeUpdate();
            System.out.println("Game record saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}