package br.com.wayon.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wayon.commons.dto.ContaCorrenteDto;
import br.com.wayon.domains.ContaCorrente;
import br.com.wayon.services.ContaCorrenteService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/conta-corrente")
@AllArgsConstructor
public class ContaCorrenteController {
	
	private ContaCorrenteService service;
	
	@PostMapping("/nova")
	public ResponseEntity<?> criarNovaContaCorrente(@RequestBody ContaCorrenteDto contaCorrente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.criarNovaContaCorrente(contaCorrente));
	}
	
//	@PutMapping("/atualiza-saldo")
//	public ResponseEntity<?> atualizaSaldo(@RequestBody ContaCorrenteDto contaCorrente) {
//		return ResponseEntity.ok(service.atualizaSaldo(contaCorrente));
//	}

}
