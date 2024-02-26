package br.com.ibm.consulting.bootcamp.demospring.service;

import br.com.ibm.consulting.bootcamp.demospring.domain.Reservas;
import br.com.ibm.consulting.bootcamp.demospring.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ReservaServico {

    @Autowired
    LivroService livroService;
    @Autowired
    ReservaRepository repository; // Instância do repositório


    public Reservas criaReserva(Reservas reservas) {

        // Verifica se o livro existe
        boolean livroExiste = livroService.verificarExistenciaLivro(reservas.getNomeLivro());
        if (!livroExiste) {
            throw new Error("Livro não encontrado " + reservas.getNomeLivro());
        }
        // Se o livro existir, cria a reserva
        return repository.saveAndFlush(reservas);
    }

    // salva a reserva e retorna o livro
    public Reservas reservaLivro(Reservas r) {
        return repository.saveAndFlush(r);

    }

    public List<Reservas> listReserva() { //Retorna uma lista de todos as Reservas no banco de dados
        return repository.findAll();
    }

    public Reservas obterReserva(long id) {
        return repository.findById(id).orElse(null); //Retorna a reserva pelo ID nome fornecido, ou null se não encontrado
    }


    public void excluirReserva(long id) {

        repository.deleteById(id); // Exclui a reserva pelo ID fornecido
    }


}
