package IHMinitiale;

import java.util.ArrayList;
import java.util.Comparator;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
    
    public void clearTasks() {
        tasks.clear();
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }
    
 // Méthode pour obtenir la liste des tâches
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Trier par date d'échéance
    public void sortByDueDate() {
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

    // Trier par priorité
    public void sortByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }
}