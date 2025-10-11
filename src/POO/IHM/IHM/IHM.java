package IHMinitiale;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class IHM extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> taskDropdown;  // menu déroulant
    private JLabel statusLabel;  // zone d'affichage

    public IHM() throws UnknownHostException, IOException {

        setTitle("Sélection de tâche du robot");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Menu déroulant pour les tâches
        taskDropdown = new JComboBox<>(new String[] { "Chargement PD terminé", "Déchargement PD terminé"/*,  "OF terminé"*/, "Chargement PF terminé", "Déchargement PF terminé", "Retourner au dock", "Quitter l'application" });
        add(taskDropdown);

        // Bouton pour exécuter la tâche
        JButton executeButton = new JButton("OK");
        executeButton.addActionListener(new ExecuteTaskListener());
        add(executeButton);

        // Zone pour afficher l'état
        statusLabel = new JLabel("");
        add(statusLabel);

        // Bouton pour ouvrir la seconde fenêtre
        JButton openStatsButton = new JButton("Statistiques d'utilisation");
        openStatsButton.addActionListener(e -> new StatsWindow());
        add(openStatsButton);

     // Bouton pour ouvrir directement le planificateur de tâches
        JButton openTaskPlannerButton = new JButton("Planificateur d'Ordres de Fabrication");
        openTaskPlannerButton.addActionListener(e -> {
            TaskAppGUI gui = new TaskAppGUI();
            gui.createAndShowGUI();  // Ouvre directement la fenêtre
        });
        add(openTaskPlannerButton);

        
        setVisible(true);
    }

    // Listener pour le bouton d'exécution de tâche
    public class ExecuteTaskListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String selectedTask = (String) taskDropdown.getSelectedItem();                    
            
            Comm co = new Comm();
            
            switch (selectedTask) {
                case "Chargement PD terminé":
                    statusLabel.setText("En cours de transport...");
                    statusLabel.setText("Arrivé à la salle 106 !");
				try {
					co.recupererProduit();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                    break;
                case "Chargement PF terminé":
                    statusLabel.setText("En cours de transport...");
                    statusLabel.setText("Arrivé au MagAuto !");
				try {
					co.deposerProduit();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                    break;
                case "Retourner au dock":
                    statusLabel.setText("Robot en cours de retour...");
                    statusLabel.setText("Arrivé au dock !");
				try {
					co.dock();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                    break;
                case "Déchargement PF terminé":
                    statusLabel.setText("Robot est disponible !");
				try {
					co.PFT();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                    break;
                 case "Déchargement PD terminé":
                    statusLabel.setText("Robot est disponible !");
				try {
					co.PDT();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                    break;   
                 /*case "OF terminé":
                     statusLabel.setText("");
 				try {
 					co.OFT();
 					statusLabel.setText("test");
 				} catch (UnknownHostException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				} catch (IOException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				} catch (InterruptedException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				} catch (ClassNotFoundException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
                     break;*/
                case "Quitter l'application":
                    System.exit(0);
                    break;
                default:
                    statusLabel.setText("Aucune tâche sélectionnée.");
            }
        }
    }

    // Deuxième fenêtre : 
    class StatsWindow extends JFrame {

        private static final long serialVersionUID = 1L;

        public StatsWindow() {
            setTitle("Statistiques d'utilisation");
            setSize(300, 150);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new FlowLayout());

            // Bouton 1
            JButton averageButton = new JButton("Durée moyenne des tâches effectuées");
            averageButton.addActionListener(e -> {
                try {
                    showAverage();
                } catch (HeadlessException | SQLException e1) {
                    e1.printStackTrace();
                }
            });
            add(averageButton);

            // Bouton 2
            JButton requestCountButton = new JButton("Nombre de requetes par operateur");
            requestCountButton.addActionListener(e -> {
                try {
                    showRequestCount();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            add(requestCountButton);

            setVisible(true);
        }

        private void showAverage() throws SQLException {
            // Logique pour afficher la moyenne (à adapter)
            JOptionPane.showMessageDialog(this, Journal_activite.temps_moyen());
        }

        private void showRequestCount() throws SQLException {
            // Logique pour afficher le nombre de requêtes par utilisateur (à adapter)
            JOptionPane.showMessageDialog(this, Journal_activite.nb_rqt());
        }
    }
}
