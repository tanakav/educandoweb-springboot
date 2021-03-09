package com.educandoweb.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.api.domain.Pedido;
import com.educandoweb.api.repositories.PedidoRepository;
import com.educandoweb.api.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		Pedido pedido;
		
		if(!pedidoRepository.findById(id).isPresent()) {
			throw new ObjectNotFoundException("Objeto nao encontrado. Tipo: "+Pedido.class.getName());
		}
		
		pedido = pedidoRepository.findById(id).get();
		
		return pedido;
	}

}
