package com.educandoweb.api.services;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.repositories.CategoriaRepository;
import com.educandoweb.api.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){
        Categoria returnedCategoria = new Categoria();

        if(categoriaRepository.findById(id).isPresent()){
            returnedCategoria = categoriaRepository.findById(id).get();
        }else{
            throw new ObjectNotFoundException("Usuario nao encontrado. Id: "+ id);
        }

        return returnedCategoria;
    }
    
    public Categoria insert(Categoria categoria) {
    	categoria.setId(null);
    	return categoriaRepository.save(categoria);
    }
    
}
