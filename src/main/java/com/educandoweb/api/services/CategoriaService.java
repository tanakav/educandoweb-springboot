package com.educandoweb.api.services;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import javassist.NotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id) throws NotFoundException{
        Categoria returnedCategoria = new Categoria();

        if(categoriaRepository.findById(id).isPresent()){
            returnedCategoria = categoriaRepository.findById(id).get();
        }else{
            throw new NotFoundException("Usuario nao encontrado");
        }

        return returnedCategoria;
    }
    
}
