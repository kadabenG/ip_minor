package be.ucll.ip_minor_task_app.repository;

import be.ucll.ip_minor_task_app.domain.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private List<Task> tasks;

    public TaskRepository() {
        tasks = new ArrayList<>();
        tasks.add(new Task("Task 1","Eerste taak", LocalDateTime.of(2020,3,21,10,00)));
        tasks.add(new Task("Task 2","Tweede taak", LocalDateTime.of(2020,3,21,16,00)));
        tasks.add(new Task("Task 3","Derde taak", LocalDateTime.of(2020,3,27,17,00)));
    }

    public List<Task> getTasks(){return tasks;}

    public void addTask(Task task){tasks.add(task);}
    public Task getTaskById(int id){
        Task x;
        try {
                x = tasks.get(id);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            return null;
        }
        return x;

    }
    public void updateTask(Task task, int id){
        try {
            tasks.set(id,task);;
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }
}
