package br.com.wayon.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wayon.commons.dto.TransferenciaDto;
import br.com.wayon.services.OperacaoFinanceiraService;
import br.com.wayon.services.TransferenciaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transferencias")
@AllArgsConstructor
public class TransferenciaController {
	
	private TransferenciaService service;
	
	@PostMapping("/nova")
	public ResponseEntity<?> novaTransferencia(@RequestBody TransferenciaDto dto){
		return ResponseEntity.ok(service.novaTransferencia(dto));
	}

}
