package be.ucll.ip_minor_task_app.repository;

import be.ucll.ip_minor_task_app.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {

    List<Task> findByTitle(String title);

    Task findById(long id);

}
