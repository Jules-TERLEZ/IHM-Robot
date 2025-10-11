package IHMinitiale;

import java.time.LocalDateTime;

public class Task {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private int priority;

    public Task(String title, /*String description,*/ LocalDateTime dueDate/*, int priority*/) {
        this.title = title;
        //this.description = description;
        this.dueDate = dueDate;
        //this.priority = priority;
    }

    // ✅ Getters nécessaires
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return title + " - " + " Date: " + dueDate;
    }
    
    /*public String toString() {
        return title + " - " + description + " (Date: " + dueDate + ", Priorité: " + priority + ")";
    }*/
}
