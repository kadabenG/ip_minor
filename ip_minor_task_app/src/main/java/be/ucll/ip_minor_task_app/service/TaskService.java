package be.ucll.ip_minor_task_app.service;

import be.ucll.ip_minor_task_app.domain.Task;
import be.ucll.ip_minor_task_app.repository.TaskRepo;
import be.ucll.ip_minor_task_app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo){
        this.taskRepo = taskRepo;
    }

    public List<Task> getTasks(){
        Iterable<Task> tasks = taskRepo.findAll();

        List<Task> taskList = new ArrayList<>();

        for(Task t : tasks) {
            taskList.add(t);
        }
        return taskList;
    }

    public void addTask(Task task){

    }

    public Task getTaskById(int id){
        return taskRepo.findById(id);
    }

    public void updateTask(int id,Task task){}

    public void saveTask(Task task) {
        taskRepo.save(task);
    }
}
