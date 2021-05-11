package com.educandoweb.api.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.api.domain.ItemPedido;
import com.educandoweb.api.domain.PagamentoComBoleto;
import com.educandoweb.api.domain.Pedido;
import com.educandoweb.api.domain.Produto;
import com.educandoweb.api.domain.enums.EstadoPagamento;
import com.educandoweb.api.repositories.ItemPedidoRepository;
import com.educandoweb.api.repositories.PagamentoRepository;
import com.educandoweb.api.repositories.PedidoRepository;
import com.educandoweb.api.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido find(Integer id) {
		Pedido pedido;
		
		if(!pedidoRepository.findById(id).isPresent()) {
			throw new ObjectNotFoundException("Objeto nao encontrado. Tipo: "+Pedido.class.getName());
		}
		
		pedido = pedidoRepository.findById(id).get();
		
		return pedido;
	}
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamento = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamento,pedido.getInstante());
		}
		
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for(ItemPedido item: pedido.getItens()) {
			Produto produto = produtoService.find(item.getProduto().getId());
			item.setDesconto(0.0);
			item.setProduto(produto);
			item.setPreco(produto.getPreco());
			item.setPedido(pedido);
		}
		
		itemPedidoRepository.saveAll(pedido.getItens());
		System.out.println(pedido);
		
		return pedido;
		
	}

}
