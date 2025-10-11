package IHMinitiale;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Planificateur {
    private static final String url = "jdbc:mysql://mariadb-std-0f571d8a7444.apps.kappsul.su.univ-lorraine.fr/Projet_POO_S7";
    private static final String username = "root";
    private static final String password = "cRYVBny2Qi";
    private static Connection connection;

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Planificateur");

        while (rs.next()) {
            String title = rs.getString("Titre");
            //String description = rs.getString("Description");
            LocalDateTime dueDate = rs.getTimestamp("Date_OF").toLocalDateTime();
            //int priority = rs.getInt("Priorite");

            //tasks.add(new Task(title, description, dueDate, priority));
            tasks.add(new Task(title, dueDate));
        }

        return tasks;
    }

    
    public static void connexionBO() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connexion réussie à la base de données.");
    }

    public void insertion(String title, /*String description,*/ LocalDateTime dueDate/*, int priority*/) throws SQLException {
        String formattedDate = dueDate.toString(); // Format ISO 8601

        // Créer la table si elle n'existe pas
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Planificateur (" +
                "Titre VARCHAR(100), " +
                "Date_OF DATETIME)");

        // Insertion
        PreparedStatement state = connection.prepareStatement(
                "INSERT INTO Planificateur (Titre, Date_OF) VALUES (?, ?)");
        state.setString(1, title);
        state.setString(2, formattedDate);
        state.executeUpdate();
        System.out.println("Tâche insérée dans la base de données.");
    }
    
    public void deleteTaskFromDB(Task task) throws SQLException {
        PreparedStatement state = connection.prepareStatement(
            "DELETE FROM Planificateur WHERE Titre = ? AND Date_OF = ?");
        state.setString(1, task.getTitle());
        state.setString(2, task.getDueDate().toString());
        state.executeUpdate();
    }


    public static Connection getConnection() {
        return connection;
    }
    
    /*// Créer la table si elle n'existe pas
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Planificateur (" +
                "Titre VARCHAR(100), " +
                "Description VARCHAR(255), " +
                "DateLimite DATETIME, " +
                "Priorite INT)");

        // Insertion
        PreparedStatement state = connection.prepareStatement(
                "INSERT INTO Planificateur (Titre, Description, DateLimite, Priorite) VALUES (?, ?, ?, ?)");
        state.setString(1, title);
        state.setString(2, description);
        state.setString(3, formattedDate);
        state.setInt(4, priority);
        state.executeUpdate();
        System.out.println("Tâche insérée dans la base de données.");
    }
    
    public void deleteTaskFromDB(Task task) throws SQLException {
        PreparedStatement state = connection.prepareStatement(
            "DELETE FROM Planificateur WHERE Titre = ? AND Description = ? AND DateLimite = ? AND Priorite = ?");
        state.setString(1, task.getTitle());
        state.setString(2, task.getDescription());
        state.setString(3, task.getDueDate().toString());
        state.setInt(4, task.getPriority());
        state.executeUpdate();
    }*/

}
