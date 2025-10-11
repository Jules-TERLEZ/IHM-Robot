package IHMinitiale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class Comm {


	   Journal_activite journal = new Journal_activite();
	
	public void recupererProduit() throws UnknownHostException, IOException, SQLException, InterruptedException, ClassNotFoundException 
	{
		//Création de la communication avec RobotOmronJN
		   Socket socket = new Socket("localhost", 1234);
		   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		   // Variables de statut
		   String debut = "En cours...";
		   String fin = "Terminée";
			
		
		String tache = "Chargement PD terminé";
        out.println("goTo s-106");
        // Envoi dans le journal d'activité
        journal.insertion(tache, debut, 0);

        long startTimeSalle108 = System.currentTimeMillis();
    

        // Temps de déplacement vers la salle 108
        
        String line1;
        while (!(line1 = in.readLine()).startsWith("Arrived at")) {
            System.out.println(line1);  // Affichage des réponses du robot
        }
        long endTimeSalle108 = System.currentTimeMillis();
        long elapsedTimeSalle108 = endTimeSalle108 - startTimeSalle108;
        System.out.println("Temps écoulé en ms : " + elapsedTimeSalle108);

        // Envoi dans le journal d'activité
        journal.insertion(tache, fin, elapsedTimeSalle108);

	}
	
	
	public void deposerProduit() throws UnknownHostException, IOException, SQLException, InterruptedException, ClassNotFoundException
	{
		//Création de la communication avec RobotOmronJN
		   Socket socket = new Socket("localhost", 1234);
		   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		   // Variables de statut
		   String debut = "En cours...";
		   String fin = "Terminée";
			
		
		String tache = "Chargement PF terminé";
        out.println("goTo MagAuto");
        // Envoi dans le journal d'activité
        journal.insertion(tache, debut, 0);

        long startTimeSalle104 = System.currentTimeMillis();

        // Temps de déplacement vers la salle 104     
        String line2;
        while (!(line2 = in.readLine()).startsWith("Arrived at")) {
            System.out.println(line2);
        }
        long endTimeSalle104 = System.currentTimeMillis();
        long elapsedTimeSalle104 = endTimeSalle104 - startTimeSalle104;
        System.out.println("Temps écoulé en ms : " + elapsedTimeSalle104);

        // Envoi dans le journal d'activité
        journal.insertion(tache, fin, elapsedTimeSalle104);

	}
	
	
	public void dock() throws UnknownHostException, IOException, InterruptedException, SQLException, ClassNotFoundException
	{
		//Création de la communication avec RobotOmronJN
	   Socket socket = new Socket("localhost", 1234);
	   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	   // Variables de statut
	   String debut = "En cours...";
	   String fin = "Terminée";
		
		String tache = "Retourner au dock";
        out.println("dock");
        // Envoi dans le journal d'activité
        journal.insertion(tache, debut, 0);

        
        long startTimeDock = System.currentTimeMillis();
        // Temps de retour au dock
        String line3;
        while (!(line3 = in.readLine()).startsWith("DockingState: Docked")) {
            System.out.println(line3);
        }
        long endTimeDock = System.currentTimeMillis();
        long elapsedTimeDock = endTimeDock - startTimeDock;
        System.out.println("Temps écoulé en ms : " + elapsedTimeDock);

        // Envoi dans le journal d'activité
        journal.insertion(tache, fin, elapsedTimeDock);
    }

	public void PFT() throws UnknownHostException, IOException, InterruptedException, SQLException, ClassNotFoundException
	{
		//Création de la communication avec RobotOmronJN
	   Socket socket = new Socket("localhost", 1234);
	   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	   // Variables de statut
	   String fin = "Terminée";
		
		String tache = "Déchargement PF terminé";

        // Envoi dans le journal d'activité
        journal.insertion(tache, fin, 0);
    }
	
	public void PDT() throws UnknownHostException, IOException, InterruptedException, SQLException, ClassNotFoundException
	{
		//Création de la communication avec RobotOmronJN
	   Socket socket = new Socket("localhost", 1234);
	   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	   // Variables de statut
	   String fin = "Terminée";
		
		String tache = "Déchargement PD terminé";

        // Envoi dans le journal d'activité
        journal.insertion(tache, fin, 0);
    }
	
	public void OFT() throws UnknownHostException, IOException, SQLException, InterruptedException, ClassNotFoundException 
	{
		//Création de la communication avec RobotOmronJN
		   Socket socket = new Socket("localhost", 1234);
		   PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		   // Variables de statut
		   String debut = "En cours...";
		   String fin = "Terminée";
			
		
		String tache = "OF terminé";
        out.println("goTo s-106");
        // Envoi dans le journal d'activité
        journal.insertion(tache, debut, 0);

        long startTimeSalle108 = System.currentTimeMillis();
    

        // Temps de déplacement vers la salle 108
        
        String line1;
        while (!(line1 = in.readLine()).startsWith("Arrived at")) {
            System.out.println(line1);  // Affichage des réponses du robot
        }
        long endTimeSalle108 = System.currentTimeMillis();
        long elapsedTimeSalle108 = endTimeSalle108 - startTimeSalle108;
        System.out.println("Temps écoulé en ms : " + elapsedTimeSalle108);

        // Envoi dans le journal d'activité
        journal.insertion(tache, fin, elapsedTimeSalle108);

	}
}
