package pt.com.sergiorebelo.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
//import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pt.com.sergiorebelo.todolist.user.IUserRepository;

@Component // Todas as classe que o spring gerencia é preciso definir como component!
public class FilterTaskAuth extends OncePerRequestFilter { // Metodo para fazer a validação usando o spring boot

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servLetPath = request.getServletPath();
        if(servLetPath.equals("/tasks/")) {
            
            // Vai pegar a autenticação (usuario e senha )
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();// substring é um metodo usado para
                                                                               // extrair
                                                                               // parte de um texto/ o trim() remove os
                                                                               // espaços em branco que existirem
            byte[] authDecode = Base64.getDecoder().decode(authEncoded); // Descodifica o "Basic: usuario"

            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            System.out.println("Authorization");
            System.out.println(username);
            System.out.println(password);

            // Validar usuario
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                // Validar Senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                     // Segua viagem
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
               

            }
        }else{
            filterChain.doFilter(request, response);
        }

    }

}

/**
 * ESTE É UM METODO JA PRE DEFINIDO PARA FAZER A AUTENTICAÇÃO MAS PODEMOS FAZER
 * TAMBEM PELO SPRING BOOT
 * public class FilterTaskAuth implements Filter { // tem um metodo pre definido
 * que temos que acessar!
 * 
 * @Override
 *           public void doFilter(ServletRequest request, ServletResponse
 *           response, FilterChain chain)
 *           throws IOException, ServletException {
 *           // Executar alguma ação
 *           System.out.println("chegou no filtro");
 *           chain.doFilter(request, response);
 * 
 *           }
 * 
 * 
 *           }
 */
