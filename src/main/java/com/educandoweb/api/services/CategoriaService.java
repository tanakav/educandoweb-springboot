package com.educandoweb.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.dto.CategoriaDto;
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
    	Categoria novaCategoria = find(categoria.getId());
    	
    	novaCategoria = updateData(novaCategoria,categoria);
    	
    	return categoriaRepository.save(novaCategoria);
    }
    
    public void delete(Integer id) {
    	find(id);    	
    	
    	try {
    		categoriaRepository.deleteById(id);
    	}catch(DataIntegrityViolationException e) {
    		throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
    	}
    }
    
    public List<CategoriaDto> findAll(){
    	List<Categoria> categorias = (List<Categoria>) categoriaRepository.findAll();
    	List<CategoriaDto> categoriasDto;
    	
    	categoriasDto = categorias.stream()
    			.map(categoria -> new CategoriaDto(categoria))
    			.collect(Collectors.toList());    	
    	
    	return categoriasDto;
    }
    
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
    	PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    	
    	return categoriaRepository.findAll(pageRequest);
    }
        
    public Categoria fromDto(CategoriaDto categoriaDto) {
    	
    	return new Categoria(categoriaDto.getId(),categoriaDto.getNome());
    }
    
    private Categoria updateData(Categoria novaCategoria, Categoria categoria) {
    	novaCategoria.setNome(categoria.getNome());
    	
    	return novaCategoria;
    }
    
}
