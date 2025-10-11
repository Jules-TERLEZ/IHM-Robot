package IHMinitiale;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Stock {
	public void showStockGUI() {
        JFrame frame = new JFrame("Contenu de la base Stock");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);

        String[] columnNames = {"Produit Fini", "Assemblage", "Écrou", "Carter", "Flasque", "Date Limite"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        try {
            Planificateur.connexionBO(); // Connexion à la base
            Connection conn = Planificateur.getConnection(); // Méthode à ajouter si elle n'existe pas
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Stock");

            while (rs.next()) {
                String produitFini = rs.getString("ProduitFini");
                String assemblage = rs.getString("Assemblage");
                String ecrou = rs.getString("Ecrou");
                String carter = rs.getString("Carter");
                String flasque = rs.getString("Flasque");
                String date = rs.getString("DateLimite");

                Object[] rowData = {produitFini, assemblage, ecrou, carter, flasque, date};
                tableModel.addRow(rowData);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage());
        }

        frame.setVisible(true);
    }
	
	public void insertion(String ProduitFini, String Assemblage, String Ecrou, String Carter, String Flasque, LocalDateTime dueDate) throws SQLException, ClassNotFoundException {
		Planificateur.connexionBO();
        String formattedDate = dueDate.toString(); // Format ISO 8601

        Connection connection = Planificateur.getConnection(); // Méthode à ajouter si elle n'existe pas
        // Créer la table si elle n'existe pas
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Stock (" +
                "ProduitFini VARCHAR(100), " +
                "Assemblage VARCHAR(100), " +
                "Ecrou VARCHAR(100), " +
                "Carter VARCHAR(100), " +
                "Flasque VARCHAR(100), " +
                "Date DATETIME)");

        // Insertion
        PreparedStatement state = connection.prepareStatement(
                "INSERT INTO Stock (ProduitFini, Assemblage, Ecrou, Carter, Flasque, DateLimite) VALUES (?, ?, ?, ?, ?, ?)");
        state.setString(1, ProduitFini);
        state.setString(2, Assemblage);
        state.setString(3, Ecrou);
        state.setString(4, Carter);
        state.setString(5, Flasque);
        state.setString(6, formattedDate);
        state.executeUpdate();
        System.out.println("Stock inséré dans la base de données.");
    }
}
