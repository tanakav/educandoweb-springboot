package com.educandoweb.api.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.api.domain.Pedido;
import com.educandoweb.api.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id){
		Pedido pedido = pedidoService.find(id);
		
		return ResponseEntity.ok().body(pedido);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido){
		pedido = pedidoService.insert(pedido);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(pedido.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	

}
