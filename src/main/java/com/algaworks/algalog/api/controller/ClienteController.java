package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return catalogoClienteService.listar();
	}
	
	@GetMapping("/{clienteId}")
	public Cliente buscarPorId(@PathVariable Long clienteId) {
		return catalogoClienteService.buscaPorId(clienteId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, 
			@Valid @RequestBody Cliente cliente){
		if(!catalogoClienteService.verificaIdExiste(clienteId))
			return ResponseEntity.notFound().build();
		
		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!catalogoClienteService.verificaIdExiste(clienteId))
			return ResponseEntity.notFound().build();

		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}
