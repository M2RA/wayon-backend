package br.com.wayon.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wayon.commons.dto.ContaCorrenteDto;
import br.com.wayon.commons.dto.OperacaoFinanceiraDto;
import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.services.ContaCorrenteService;
import br.com.wayon.services.OperacaoFinanceiraService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/operacoes")
@AllArgsConstructor
public class OperacaoFinanceiraController {
	
	private OperacaoFinanceiraService service;
	
	@PostMapping("/nova")
	public ResponseEntity<?> criarNovaContaOperacao(@RequestBody OperacaoFinanceiraDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.criarNovaOperacao(dto));
	}
	
	@GetMapping("/extrato/{contaCorrente}")
	public ResponseEntity<?> getExtrato(@PathVariable String contaCorrente) {
		return ResponseEntity.ok(service.getExtrato(contaCorrente));
	}
}
