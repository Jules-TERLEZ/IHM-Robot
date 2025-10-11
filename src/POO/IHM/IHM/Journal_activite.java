 package IHMinitiale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Journal_activite 
{
	static private String url = "jdbc:mysql://mariadb-std-0f571d8a7444.apps.kappsul.su.univ-lorraine.fr/Projet_POO_S7";
	static private String username = "root";
	static private String password = "cRYVBny2Qi";
	static private Statement statement;
	static private Connection connection;
	
	public static void connexionBO() throws ClassNotFoundException, SQLException
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	connection = DriverManager.getConnection(url, username, password);
	statement = connection.createStatement();
	System.out.println("you are connected to the DB");
	}
	
	
	public void insertion(String tache, String statut, long ElapsedTime) throws SQLException
	{
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
        String strDate = sdf.format(cal.getTime());
        
      //Requête pour créer la table
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Journal_activite (Utilisateur VARCHAR(40), Tache VARCHAR(40), Statut VARCHAR(40), Temps LONG, Date TEXT)");
      
      PreparedStatement state = connection.prepareStatement("INSERT INTO Journal_activite VALUES (?, ?, ?, ?, ?)");
      state.setString(1, System.getProperty("user.name"));
      state.setString(2, tache);      
      state.setString(3, statut);
      state.setLong(4, ElapsedTime);
      state.setString(5, strDate);
      state.executeUpdate();
	}
	
	static public long temps_moyen() throws SQLException
	{
		long somme=0;
		long stmtlong=0;
		int nb=0;
		ResultSet resultSet = statement.executeQuery("SELECT * FROM `Journal_activite` WHERE `Temps`");
		while (resultSet.next()) 
		{
			stmtlong=resultSet.getLong(4);
			somme = somme + stmtlong;
			nb++;
		}
		long moyenne;
		moyenne=somme/nb;
		return moyenne;
	}
	
	static public long nb_rqt() throws SQLException {
		String rqt = ("SELECT * FROM `Journal_activite` WHERE `Statut`='En cours...' AND `Utilisateur`='" + System.getProperty("user.name") + "'");
		ResultSet resultSet = statement.executeQuery(rqt);
		int nb=0;
		while (resultSet.next()) 
		{
			nb++;
		}
		return nb;
	}
}