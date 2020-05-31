package be.ucll.ip_minor_task_app.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;

@Entity
public class SubTask {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Size(min=3, max = 50)
    private String description;


    public SubTask(String title,String description){
        setTitle(title);
        setDescription(description);
    }

    public SubTask(){}

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return getTitle() + ":   " + getDescription();
    }
}

