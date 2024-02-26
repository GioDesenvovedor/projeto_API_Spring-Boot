package br.com.ibm.consulting.bootcamp.demospring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.repository.LivroRepository;


// Indica que esta classe é um componente gerenciado pelo Spring
@Component
public class LivroService {

    // Injeção de dependência para instanciar automaticamente o repositório
    @Autowired

    LivroRepository repository; // Instância do repositório para realizar operações no banco de dados

    public Livro criarLivro(Livro l) {  // Salva e retorna o livro criado
        return repository.saveAndFlush(l);
    }

    public List<Livro> listar() { // Retorna uma lista de todos os livros no banco de dados
        return repository.findAll();
    }

    public Livro obter(long id) {
        return repository.findById(id).orElse(null); // Retorna o livro pelo ID fornecido, ou null se não encontrado
    }

    public void alterar(long id, Livro novoLivro) {

        // Cria um novo objeto Livro com o ID fornecido e os detalhes do novo livro
        var alterado = new Livro(id, novoLivro.getAutor(), novoLivro.getTitulo(), novoLivro.getAnoPublicacao());
        repository.saveAndFlush(alterado);
    }

    // Cria um novo objeto Livro com o ID fornecido (apenas necessário para exclusão)
    public void excluir(long id) {
        var excluir = new Livro(id, null, null, null);
        repository.delete(excluir);
    }


    // Método para verificar se um livro com o título fornecido existe
    public boolean verificarExistenciaLivro(String tituloLivro) {
        Livro livro = repository.findByTitulo(tituloLivro);
        return livro != null; // Retorna verdadeiro se o livro existir, falso caso contrário
    }

    public long contarLivros() {
        return repository.count();
    }

}
