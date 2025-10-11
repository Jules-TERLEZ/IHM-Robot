package IHMinitiale;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class TaskAppGUI {
    private TaskManager manager = new TaskManager();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    
    private void loadTasksFromDB() {
        try {
            Planificateur.connexionBO();
            Planificateur planif = new Planificateur();
            List<Task> tasksFromDB = planif.getAllTasks();

            manager.clearTasks(); // vide la liste existante
            for (Task task : tasksFromDB) {
                manager.addTask(task);
            }
            updateTaskListView();
        } catch (Exception ex) {
            showError("Erreur DB", "Impossible de charger les ordres de fabrication : " + ex.getMessage());
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskAppGUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
    	loadTasksFromDB(); // Charger les tâches avant affichage

        JFrame frame = new JFrame("Planificateur d'ordres de fabrication");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField titleField = new JTextField();
        //JTextField descriptionField = new JTextField();
        JTextField dateField = new JTextField();
        //JTextField priorityField = new JTextField();
        JButton addButton = new JButton("Ajouter ordre de fabrication");

        panel.add(new JLabel("Titre:")); panel.add(titleField);
        //panel.add(new JLabel("Description:")); panel.add(descriptionField);
        panel.add(new JLabel("Date_OF (yyyy-MM-dd HH:mm):")); panel.add(dateField);
        //panel.add(new JLabel("Priorité (1=haute, 2=moyenne, 3=basse):")); panel.add(priorityField);
        panel.add(addButton);

        JList<String> taskListView = new JList<>(listModel);
        // Permettre la sélection multiple
        taskListView.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskListView);


        JButton sortByDateButton = new JButton("Trier par date");
        //JButton sortByPriorityButton = new JButton("Trier par priorité");
        JButton deleteButton = new JButton("Supprimer ordre de fabrication");

        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                //String description = descriptionField.getText();
                String dateStr = dateField.getText();
                //int priority = Integer.parseInt(priorityField.getText());

                LocalDateTime dueDate = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                //Task task = new Task(title, description, dueDate, priority);
                Task task = new Task(title, dueDate);

                // Ajouter en mémoire
                manager.addTask(task);
                updateTaskListView();

                // Ajouter à la base de données
                Planificateur.connexionBO();
                Planificateur planif = new Planificateur();
                //planif.insertion(title, description, dueDate, priority);
                planif.insertion(title, dueDate);
                
                // Réinitialiser les champs
                titleField.setText("");
                //descriptionField.setText("");
                dateField.setText("");
                //priorityField.setText("");
            } catch (Exception ex) {
                showError("Erreur", "Veuillez entrer des valeurs valides.\n" + ex.getMessage());
            }
        });

        deleteButton.addActionListener(e -> {
            int[] selectedIndices = taskListView.getSelectedIndices();
            if (selectedIndices.length > 0) {
                int confirmation = JOptionPane.showConfirmDialog(null,
                    "Êtes-vous sûr de vouloir supprimer ces ordres de fabrication ?",
                    "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION);

                if (confirmation == JOptionPane.YES_OPTION) {
                    try {
                        Planificateur.connexionBO();
                        Planificateur planif = new Planificateur();

                        for (int i = selectedIndices.length - 1; i >= 0; i--) {
                            int index = selectedIndices[i];
                            Task task = manager.getTasks().get(index);
                            planif.deleteTaskFromDB(task); // suppression dans la base
                            manager.removeTask(index);     // suppression dans la mémoire
                        }
                        updateTaskListView();
                    } catch (Exception ex) {
                        showError("Erreur DB", "Erreur : " + ex.getMessage());
                    }
                }
            } else {
                showError("Sélection", "Veuillez sélectionner au moins un ordre de fabrication à supprimer.");
            }
        });




        sortByDateButton.addActionListener(e -> {
            manager.sortByDueDate();
            updateTaskListView();
        });

        /*sortByPriorityButton.addActionListener(e -> {
            manager.sortByPriority();
            updateTaskListView();
        });*/


        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(sortByDateButton);
      //buttonPanel.add(sortByPriorityButton);
        buttonPanel.add(deleteButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void updateTaskListView() {
        listModel.clear();
        for (Task task : manager.getTasks()) {
            listModel.addElement(task.toString());
        }
    }

    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
     public void checkTasksDueNow() {
        	Comm co = new Comm();
            try {
                Planificateur.connexionBO();
                Planificateur planif = new Planificateur();
                List<Task> tasks = planif.getAllTasks();

                LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0); // Ignore secondes & millisecondes

                for (Task task : tasks) {
                    LocalDateTime due = task.getDueDate().withSecond(0).withNano(0); // Même précision

                    if (now.equals(due)) {
                    	co.OFT();
                    }
                }

            } catch (Exception e) {
                System.err.println("Erreur lors de la vérification des ordres de fabrication : " + e.getMessage());
            }
        }
}
