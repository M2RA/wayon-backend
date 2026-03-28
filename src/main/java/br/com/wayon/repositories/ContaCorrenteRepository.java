package br.com.wayon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.domains.pk.ContaCorrentePK;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, ContaCorrentePK>{

}
