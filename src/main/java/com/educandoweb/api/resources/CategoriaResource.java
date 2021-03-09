package com.educandoweb.api.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.api.domain.Categoria;
import com.educandoweb.api.services.CategoriaService;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(
        value="/{id}",
        method = RequestMethod.GET
    )
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
        Categoria responseData = new Categoria();
        
        responseData = categoriaService.buscar(id);

        return ResponseEntity.ok().body(responseData);
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
    	Categoria novaCategoria = categoriaService.insert(categoria);
    	
    	URI uri = ServletUriComponentsBuilder
    			.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(novaCategoria.getId())
    			.toUri();
    	
    	return ResponseEntity.created(uri).build();
    	
    }
}
