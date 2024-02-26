package br.com.ibm.consulting.bootcamp.demospring.repository;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaresRepository extends JpaRepository<Exemplares, Integer> {
}
