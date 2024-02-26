package br.com.ibm.consulting.bootcamp.demospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.service.LivroService;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
	
	@Autowired
	LivroService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //Define o código de status da resposta como 201 - Created
	public Livro criar(@RequestBody Livro livro) {
		return service.criarLivro(livro);
	}

	// Método GET para listar todos os livros
	@GetMapping
	public List<Livro> listar() {
		return service.listar(); // Chama o serviço para listar todos os livros e retorna a lista
	}

	// Método GET para obter um livro por ID
	@GetMapping("/{id}")
	public ResponseEntity<Livro> obter(@PathVariable long id) {
		var livro = service.obter(id); // Chama o serviço para obter o livro pelo ID fornecido
		if (livro != null) {
			return new ResponseEntity<Livro>(livro, HttpStatus.OK);// Retorna o livro encontrado com o código de status 200 - OK
		}
		return new ResponseEntity<Livro>(livro, HttpStatus.NOT_FOUND);// Retorna um código de status 404 - Not Found se o livro não for encontrado
	}

	// Método PUT para atualizar um livro existente por ID
	@PutMapping("/{id}") // Define o código de status da resposta como 204 - No Content
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable long id) {
		var livroExistente = service.obter(id); // Obtém o livro existente pelo ID fornecido
		if (livroExistente != null) {
			service.alterar(id, livro); // Chama o serviço para atualizar o livro
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // Retorna uma resposta vazia com o código de status 204 - No Content
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); // Retorna um código de status 404 - Not Found se o livro não for encontrado
	}

	// Método DELETE para excluir um livro por ID
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // Define o código de status da resposta como 204 - No Content
	public ResponseEntity<Void> excluir(@PathVariable long id) {
		var livroExistente = service.obter(id); // Obtém o livro existente pelo ID fornecido
		if (livroExistente != null) {
			service.excluir(id); // Chama o serviço para excluir o livro
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // Retorna uma resposta vazia com o código de status 204 - No Content
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); // Retorna um código de status 404 - Not Found se o livro não for encontrado
	}
}
