package com.educandoweb.api.repositories;

import com.educandoweb.api.domain.Categoria;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria,Integer>{
    
}
