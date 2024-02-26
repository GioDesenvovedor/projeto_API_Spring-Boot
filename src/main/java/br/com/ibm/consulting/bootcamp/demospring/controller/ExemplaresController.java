package br.com.ibm.consulting.bootcamp.demospring.controller;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplares;
import br.com.ibm.consulting.bootcamp.demospring.service.ExemplaresServico;
import br.com.ibm.consulting.bootcamp.demospring.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/livros/{id}/exemplares")
public class ExemplaresController {
    @Autowired
    ExemplaresServico exemplaresServico;
    @Autowired
    LivroService livroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exemplares incluirNuExemplar(@RequestBody Exemplares exemplares) {
        return exemplaresServico.incluirCopia(exemplares);
    }

    // Método GET para obter um livro por ID
    @GetMapping("/{quantidade}")
    public ResponseEntity<Exemplares> obter(@PathVariable Integer id) {
        var exemplar = exemplaresServico.obterExemplar(id); // Chama o serviço pelo ID fornecido
        if (exemplar != null) {
            return new ResponseEntity<Exemplares>(exemplar, HttpStatus.OK); // Retorna o reserva encontrado com o código de status 200 - OK

        }
        return new ResponseEntity<Exemplares>(exemplar, HttpStatus.NOT_FOUND);// Retorna um código de status 404 - Not Found se o livro não for encontrado
    }

    @GetMapping("/{exemplares}")
    public ResponseEntity<Long> obterQuantidadeLivros() {
        long quantidadeLivros = livroService.contarLivros();
        return ResponseEntity.ok(quantidadeLivros);
    }

    @DeleteMapping("/{exemplares}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Define o código de status da resposta como 204 - No Content
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        var livroExistente = exemplaresServico.obterExemplar(id); // Obtém reserva existente pelo ID fornecido
        if (livroExistente != null) {
            exemplaresServico.excluirExemplar(id); // Chama o serviço para excluir o reserva
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // Retorna uma resposta vazia com o código de status 204 - No Content
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); // Retorna um código de status 404 - Not Found se o livro não for encontrado
    }
}
