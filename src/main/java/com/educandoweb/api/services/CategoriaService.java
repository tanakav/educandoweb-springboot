package com.educandoweb.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.repositories.CategoriaRepository;
import com.educandoweb.api.services.exceptions.DataIntegrityException;
import com.educandoweb.api.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
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
    
    public Categoria update(Categoria categoria) {
    	find(categoria.getId());
    	return categoriaRepository.save(categoria);
    }
    
    public void delete(Integer id) {
    	find(id);    	
    	
    	try {
    		categoriaRepository.deleteById(id);
    	}catch(DataIntegrityViolationException e) {
    		throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
    	}
    }
    
}
