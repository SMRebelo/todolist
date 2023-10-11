package pt.com.sergiorebelo.todolist.user;

import lombok.Data;

// @Getters -> coloca apenas getters 
// @Setters -> coloca apenas setters

@Data // coloca getters e setters
public class UserModel {

    private String username;
    private String name;
    private String password;

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
