package br.com.ibm.consulting.bootcamp.demospring.domain;

import jakarta.persistence.*;

@Entity
@Table
public class Exemplares {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer quantidade = 0;

    private String nomeLivro;
    public Exemplares(){

    }

    public Exemplares(long id, Integer quantidade, String nomeLivro) {
        this.id = id;
        this.quantidade = quantidade;
        this.nomeLivro = nomeLivro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }
}
