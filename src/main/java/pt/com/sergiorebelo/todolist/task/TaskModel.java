package pt.com.sergiorebelo.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * ID
 * Usuário (ID_USUARIO)
 * Descrição
 * Título
 * Data de Inicio
 * Data de Terminar
 * Prioridade
 */
@Data
@Entity(name = "tb_tasks")

public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID") // cria um id usuario automaticamente

    private UUID id;
    private String description;

    @Column(length = 50) // Limita o numero de caracteres to titulo 
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 50 ){
            throw new Exception("O campo title deve conter no maximo 50 caracteres");
        }
        this.title = title;
    }

}
