package pt.com.sergiorebelo.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /** tipo de restorno que este metodo "requestMapping" vai restornar. ->  podem ser:
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
    public UserModel create(@RequestBody UserModel UserModel){ // vai recerber os dados do usuario que quero cadastrar. É PRECISO CRIAR UM OBJECTO OU CLASSE ONDE VAI ESTAR A ESTRUTURA DO USER
      var user = this.userRepository.findByUsername(UserModel.getUsername());// Valida de o nome que queremos criar no usuario já existe!
      if(user != null){
        System.out.println("usuario já existe");
      }
       var userCreated = this.userRepository.save(UserModel);
       return userCreated;
    }
}
