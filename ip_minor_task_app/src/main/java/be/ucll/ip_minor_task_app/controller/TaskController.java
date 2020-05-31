package be.ucll.ip_minor_task_app.controller;

import be.ucll.ip_minor_task_app.domain.Task;
import be.ucll.ip_minor_task_app.domain.SubTask;
import be.ucll.ip_minor_task_app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping
    public String getTasks(Model model){
        model.addAttribute("tasks",taskService.getTasks());
        return "tasks";
    }


    @GetMapping("/{id}")
    public String getTaskDetail(Model model, @PathVariable("id") int id){
        if (taskService.getTaskById(id)==null){
            model.addAttribute("error","Something went wrong my G");
        }else model.addAttribute("task",taskService.getTaskById(id));
        return "taskDetail";
    }


    @GetMapping("/edit/{id}")
    public String getEditForm(Model model, @PathVariable("id") int id){
        if (taskService.getTaskById(id)==null){
            model.addAttribute("error","Something went wrong my G");
            return "redirect:/tasks";
        }else {
            model.addAttribute("task",taskService.getTaskById(id));
            model.addAttribute("id",id);
        }
        return "taskEditForm";
    }


    @PostMapping("/update/{id}")
    public String editTask(@ModelAttribute @Valid Task task, BindingResult bindingResult,@PathVariable("id") Long id){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for (ObjectError o: bindingResult.getAllErrors()){
                System.out.println(o.toString());
            }
            return "taskEditForm";
        }
        Task oldTask = taskService.getTaskById(Math.toIntExact(id));
        for(SubTask st: oldTask.getSubTasks()){
            task.addSubTask(st);
        }
        task.setId(id);
        taskService.saveTask(task);
        return "redirect:/tasks/" + id.toString();
    }


    @GetMapping("/new")
    public String getTaskFrom(Model model){
        model.addAttribute("task",new Task());
        return "taskForm";
    }


    @PostMapping
    public String addTask(@ModelAttribute @Valid Task task, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for (ObjectError o: bindingResult.getAllErrors()){
                System.out.println(o.toString());
            }
            return "taskForm";
        }
        taskService.saveTask(task);
        return "redirect:/tasks";
    }


    @GetMapping("/{id}/sub/create")
    public String getSubTaskFrom(Model model, @PathVariable("id") int id){
        model.addAttribute("subTask",new SubTask());
        return "subTaskForm";
    }


    @PostMapping("/{id}/sub/create")
    public String addSubTask(@ModelAttribute @Valid SubTask subTask, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for (ObjectError o: bindingResult.getAllErrors()){
                System.out.println(o.toString());
            }
            return "subTaskForm";
        }
        Task task = taskService.getTaskById(id);
        task.addSubTask(subTask);
        taskService.saveTask(task);
        return "redirect:/tasks/" + Integer.toString(id);
    }
}
