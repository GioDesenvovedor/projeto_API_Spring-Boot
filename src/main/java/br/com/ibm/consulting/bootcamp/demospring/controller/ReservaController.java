package br.com.ibm.consulting.bootcamp.demospring.controller;
import br.com.ibm.consulting.bootcamp.demospring.domain.Reservas;
import br.com.ibm.consulting.bootcamp.demospring.service.LivroService;
import br.com.ibm.consulting.bootcamp.demospring.service.ReservaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros/{id}/reservas")
public class ReservaController {


    @Autowired
    ReservaServico reservaServico;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Define o código de status da resposta como 201 - Created
    public Reservas criaReserva(@RequestBody  Reservas reservas){
        return reservaServico.criaReserva(reservas);
    }


    // Método GET para listar todos as reservas
    @GetMapping
    public List<Reservas> listar() {
        return reservaServico.listReserva(); // Chama o serviço para listar todos as reservas e retorna a lista
    }

    // Método GET para obter um livro por ID
    @GetMapping("/{usuario}")
    public ResponseEntity<Reservas> obter(@PathVariable long id) {
        var reserva = reservaServico.obterReserva(id); // Chama o serviço pelo ID fornecido
        if (reserva != null) {
            return new ResponseEntity<Reservas>(reserva, HttpStatus.OK); // Retorna o reserva encontrado com o código de status 200 - OK

        }
          return new ResponseEntity<Reservas>(reserva, HttpStatus.NOT_FOUND);// Retorna um código de status 404 - Not Found se o livro não for encontrado
    }


    @DeleteMapping("/{usuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Define o código de status da resposta como 204 - No Content
    public ResponseEntity<Void> excluir(@PathVariable long id) {
        var livroExistente = reservaServico.obterReserva(id); // Obtém reserva existente pelo ID fornecido
        if (livroExistente != null) {
            reservaServico.excluirReserva(id); // Chama o serviço para excluir o reserva
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // Retorna uma resposta vazia com o código de status 204 - No Content
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); // Retorna um código de status 404 - Not Found se o livro não for encontrado
    }

}