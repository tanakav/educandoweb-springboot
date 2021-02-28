package com.educandoweb.api.repositories;

import com.educandoweb.api.domain.Cliente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Integer>{
    
}
