package pt.com.sergiorebelo.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Modificador
 * public
 * private
 * protected
 */
@RestController // Usado para acessar os controles
@RequestMapping("/users")
public class UserController {

  @Autowired // gerencia o ciclo de vida
  private IUserRepository userRepository; // instacia o metodo IUserrepository

  /**
   * tipo de restorno que este metodo "requestMapping" vai restornar. -> podem
   * ser:
   * String (texto)
   * integer (int) numeros inteiros
   * double (double) numeros fracionados
   * float (float) igual ao double só muda o numero de caracteres
   * char (A B C D ect)
   * date (datas)
   * bolean (true or false)
   * void () nao retorna nada
   */
  /**
   * body
   */
  @RequestMapping("/")
  // ResponseEntity ao inves de UserModel porque dentro do mesmo pedido podemos
  // ter um sucesso e um erro !
  public ResponseEntity create(@RequestBody UserModel UserModel) { // vai recerber os dados do usuario que quero
                                                                   // cadastrar. É PRECISO CRIAR UM OBJECTO OU CLASSE
                                                                   // ONDE VAI ESTAR A ESTRUTURA DO USER
    var user = this.userRepository.findByUsername(UserModel.getUsername());// Valida de o nome que queremos criar no
                                                                           // usuario já existe!

    if (user != null) {
      // mensagem de erro
      // status CODE
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario já existe!");// retorna mensagem de erro. o
                                                                                      // httpStatus permite ver que
                                                                                      // mensagem de erro existem
    }
    var passwordHashred = BCrypt.withDefaults().hashToString(12, UserModel.getPassword().toCharArray()); // Cryptografa a password! 

    UserModel.setPassword(passwordHashred);

    var userCreated = this.userRepository.save(UserModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
}
