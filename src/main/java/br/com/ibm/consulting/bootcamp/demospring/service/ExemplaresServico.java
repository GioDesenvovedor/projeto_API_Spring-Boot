package br.com.ibm.consulting.bootcamp.demospring.service;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplares;
import br.com.ibm.consulting.bootcamp.demospring.repository.ExemplaresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExemplaresServico {
    @Autowired
    ExemplaresRepository exemplaresRepository;
    @Autowired
    LivroService livroService;


    public Exemplares incluirCopia(Exemplares exemplares) {
        boolean livroExiste = livroService.verificarExistenciaLivro(exemplares.getNomeLivro());
        if (!livroExiste) {
            throw new Error("Exemplar não encontrado " + exemplares.getNomeLivro());
        }
        // Aumenta a quantidade de exemplares (livros) existentes
        var quantidade = new Exemplares();
        quantidade.setQuantidade(+ quantidade.getQuantidade());
        return exemplaresRepository.saveAndFlush(exemplares);

    }

    public Exemplares obterExemplar(Integer id) {
        return exemplaresRepository.findById(id).orElse(null);
    }

    public void excluirExemplar(Integer id) {
        var excluir = new Exemplares(id, null, null);
        exemplaresRepository.delete(excluir); // Exclui a exemplar pelo ID fornecido
    }
}
