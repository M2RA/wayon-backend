package br.com.wayon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.wayon.domains.OperacaoFinanceira;

@Repository
public interface OperacaoFinanceiraRepository extends JpaRepository<OperacaoFinanceira, Long>{
	
	@Query("select operacoes "
			+ "from OperacaoFinanceira operacoes "
			+ "where 1 = 1 "
			+ "and operacoes.contaCorrente.contaCorrente = :contaCorrente")
	public List<OperacaoFinanceira> getExtrato(Long contaCorrente);
}
