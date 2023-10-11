package pt.com.sergiorebelo.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraRota")
//http://localhost:8080/----- "ROTA DA APLICAÇÂO"
public class MinhaPrimeiraController {

    /**
     * METODOS DE ACESSO HTTP
     * GET - procura uma informaçao 
     * POST - Adicionar um dado/informação
     * PUT - Alterar um dado/inf
     * DELETE - Remover um dado
     * PATCH - Alterar somente uma parte da inf/dado
     */
    

    //METODO (FUNCIONALIDADE) DE UMA CLASSE
    @GetMapping("/primeiroMetodo")
    public String primeiraMensagem(){

        return "Funcionou";
    }
}
