package IHMinitiale;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Journal_activite.connexionBO();
		
		TaskAppGUI checker = new TaskAppGUI();
	    checker.checkTasksDueNow();
		
		 SwingUtilities.invokeLater(() -> {
				try {
					new IHM();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

	}

}
