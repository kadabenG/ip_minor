package be.ucll.ip_minor_task_app.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Size(min=3, max = 50)
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime dueDate;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<SubTask> subTasks;

    public Task(String title,String description, LocalDateTime dueDate){
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        subTasks = new ArrayList<>();
    }

    public Task(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime d) {
        this.dueDate = d;
    }

    public void addSubTask(SubTask t){
        if (this.subTasks == null){
            subTasks = new ArrayList<>();
        }
        this.subTasks.add(t);
    }

    public List<SubTask> getSubTasks() {
        return this.subTasks;
    }

    @Override
    public String toString() {
//        DateTimeFormatter.ofLocalizedDateTime('DD MM YYYY 11:05:30')
        return getTitle() + ": due " + getDueDate().toLocalDate().toString() + " at " + getDueDate().toLocalTime().toString();
    }
}
