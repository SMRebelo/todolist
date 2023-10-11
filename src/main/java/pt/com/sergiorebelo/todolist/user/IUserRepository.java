package pt.com.sergiorebelo.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

// interface ao contrario da Class só tem a representaão dos dados dentro dela 
// o JPA já tem bibliotecas para fazer a comunicação com a base de dados 
// O sina <> siginifica que a classe já recebe um genarator(são atributos mais dinamicos)
public interface IUserRepository extends JpaRepository<UserModel, UUID> { 
   UserModel findByUsername(String username);
}
