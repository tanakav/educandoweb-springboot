package com.educandoweb.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.domain.Produto;
import com.educandoweb.api.repositories.CategoriaRepository;
import com.educandoweb.api.repositories.ProdutoRepository;
import com.educandoweb.api.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado! Id: "+id+", Tipo: "+Produto.class.getName()));
		
		return produto;
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return produtoRepository.search(nome, categorias, pageRequest);
	}

}
