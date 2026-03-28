package br.com.wayon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.domains.pk.ContaCorrentePK;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, ContaCorrentePK>{

}
