package com.educandoweb.api.repositories;

import com.educandoweb.api.domain.Endereco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco,Integer>{
    
}
