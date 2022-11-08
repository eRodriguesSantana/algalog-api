package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private CatalogoClienteService catalogoClienteService;
	private EntregaRepository entregaRepository;
	private ModelMapper modelMapper;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscaPorId(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
	
	@Transactional
	public List<Entrega> listar(){
		return entregaRepository.findAll();
	}
	
	@Transactional
	public ResponseEntity<EntregaModel> buscaPorId(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> {
					EntregaModel entregaModel = modelMapper.map(entrega, EntregaModel.class);
					
					return ResponseEntity.ok(entregaModel);
				})
				.orElseThrow(() -> new NegocioException("Entrega não encontrada"));
	}
}
