package pt.com.sergiorebelo.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.com.sergiorebelo.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class taskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity created(@RequestBody TaskModel taskModel, HttpServletRequest request) {

        var idUser = request.getAttribute("idUser"); // associa o UUID ao idUser e verifa se o usuario esta log in
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) { // verifica se a
                                                                                                        // data que
                                                                                                        // estamos a
                                                                                                        // enserir é
                                                                                                        // maior que a
                                                                                                        // data da
                                                                                                        // criação
                                                                                                        // juntamento
                                                                                                        // com a data de
                                                                                                        // fim
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio/Fim deve ser maior do que a data actual");
        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) { // verifica se a data final é maior do que a data
                                                                    // inicial
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio deve ser menor qu a data do fim ");
        }
        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) { // ASSOCIA UMA TAREFA A UM USER
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}") // insere o id da tarefa para o url
    public TaskModel update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
        // este penultimo argumento saca o id da tarefa para o url - > EDITA UMA TAREFA

        var task = this.taskRepository.findById(id).orElse(null);

        Utils.copyNonNullProperties(taskModel, task);

        return this.taskRepository.save(task);

    }
}
