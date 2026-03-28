package br.com.wayon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wayon.domains.OperacaoFinanceira;

@Repository
public interface OperacaoFinanceiraRepository extends JpaRepository<OperacaoFinanceira, Long>{
}
