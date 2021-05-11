package com.educandoweb.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.domain.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
    
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT produto "
			+ "FROM Produto produto "
			+ "INNER JOIN produto.categorias categorias "
			+ "WHERE produto.nome LIKE %:nome% AND "
			+ "categorias IN :categorias")
	Page<Produto> search(@Param("nome") String nome,@Param("categorias") List<Categoria> ids, Pageable pageRequest);	
//	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
