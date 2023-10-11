package pt.com.sergiorebelo.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// @Getters -> coloca apenas getters 
// @Setters -> coloca apenas setters

@Data // coloca getters e setters
@Entity(name="tb_users") // cria uma tabela no banco de dados H2
public class UserModel {
    @Id // chave primaria 
    @GeneratedValue(generator = "UUID") // gera o ID de forma automatica 
    private UUID id;
    
    //@Column(name = "usuario") -> cria uma tabela na base de dados com este nome. Ao inves de automaticamente ser criada pelos strings abaixo que ele automaticamente entende como colunas da tabela que estamos a criar
    @Column(unique = true) // Significa que a coluna na base de dados vai ter um constrain que impede de haver usuarios igual. cada usuario vai ser unico 
    private String username;
    private String name;
    private String password;

    @CreationTimestamp // Quando o dado é gerado de forma automatica a base de dados já tem a informação de quando o dado é criado 
    private LocalDateTime createdAt;

    // getters e setters para aceder a classes PRIVADAS

   /** public void setUsername(String username) { // Metodo para inserir user name 
        this.username = username;
    }
    public String getUsername() { // Metodo para restornar user name 
        return username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    */
}
