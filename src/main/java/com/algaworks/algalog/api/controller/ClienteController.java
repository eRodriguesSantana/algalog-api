package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente c1 = new Cliente();
		c1.setId(1L);
		c1.setNome("Eduardo");
		c1.setTelefone("12 111122222");
		c1.setEmail("edu@edu.com");
		
		Cliente c2 = new Cliente();
		c2.setId(2L);
		c2.setNome("Eduardo2");
		c2.setTelefone("12 111122222");
		c2.setEmail("edu2@edu.com");
		
		return Arrays.asList(c1, c2);
	}
}
